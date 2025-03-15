package com.fady.data.repo.impl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.fady.data.dataSoure.local.dataBase.dao.ApartmentDao
import com.fady.data.dataSoure.local.dataBase.dao.OwnerDao
import com.fady.data.dataSoure.remote.ApiService
import com.fady.data.dto.ApartmentDto
import com.fady.data.networkConnectivity.IConnectivityHandler
import com.fady.data.networkConnectivity.NetworkConnectivityStatus
import com.fady.data.repo.base.IApartmentRepo
import com.fady.data.repo.impl.FavouriteApartmentRepo.Companion.FAVOURITE_APARTMENTS
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class ApartmentRepo @Inject constructor(
    private val apartmentDao: ApartmentDao,
    private val ownerDao: OwnerDao,
    connectivityHandler: IConnectivityHandler,
    private val dataStore: DataStore<Preferences>,
    private val apiService: ApiService
) : IApartmentRepo {

    private val isNetworkConnected = connectivityHandler.isNetworkAvailable()

    private val favApartmentIdsFlow = dataStore.data.map {
        it[FAVOURITE_APARTMENTS] ?: emptySet()
    }.distinctUntilChanged()

    override suspend fun getAllApartments(): Flow<List<ApartmentDto>> {


        return when (isNetworkConnected) {
            NetworkConnectivityStatus.CONNECTED -> combine(
                favApartmentIdsFlow,
                flowOf(apiService.getAllApartments())
            ) { favApartmentIds, apartments ->
                apartments.onEach { it.isFavourite = favApartmentIds.contains(it.id.toString()) }
            }

            NetworkConnectivityStatus.NOT_CONNECTED -> combine(
                favApartmentIdsFlow,
                apartmentDao.getAllApartmentsFlow()
                    .map { list -> list.map { it.toApartment() } }
            ) { favApartmentIds, apartments ->
                apartments.onEach { it.isFavourite = favApartmentIds.contains(it.id.toString()) }
            }
        }
    }


    private suspend fun getApartmentsFlow() = flowOf(apiService.getAllApartments())

    override suspend fun getApartmentById(id: Int): ApartmentDto = when (isNetworkConnected) {
            NetworkConnectivityStatus.CONNECTED -> apiService.getApartmentById(id)
            NetworkConnectivityStatus.NOT_CONNECTED -> apartmentDao.getApartmentById(id)
                .toApartment()
        }

    private suspend fun saveApartmentsLocally(apartments: List<ApartmentDto>) {
        val owners = apartments.mapNotNull { it.owner }
        apartments.forEach { apartment ->
            apartment.ownerId = apartment.owner?.id
        }
        ownerDao.upsertOwner(owners)
        apartmentDao.upsertApartment(apartments)
    }

}