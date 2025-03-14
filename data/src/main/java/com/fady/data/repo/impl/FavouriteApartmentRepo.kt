package com.fady.data.repo.impl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.fady.data.dataSoure.local.dao.ApartmentDao
import com.fady.data.dataSoure.remote.ApiService
import com.fady.data.di.FavouriteApartmentDataStore
import com.fady.data.dto.ApartmentDto
import com.fady.data.networkConnectivity.IConnectivityHandler
import com.fady.data.networkConnectivity.NetworkConnectivityStatus
import com.fady.data.repo.base.IFavouriteApartmentRepo
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map


class FavouriteApartmentRepo @Inject constructor(
    private val apiService: ApiService,
    connectivityHandler: IConnectivityHandler,
    private val apartmentDao: ApartmentDao,
    @FavouriteApartmentDataStore private val dataStore: DataStore<Preferences>
) : IFavouriteApartmentRepo {

    private val isConnected = connectivityHandler.isNetworkAvailable()


    companion object {
        val FAVOURITE_APARTMENTS = stringSetPreferencesKey("favourite_apartments")
    }

    override suspend fun getAllFavouriteApartments(): Flow<List<ApartmentDto>> {
        val favApartmentIdsFlow = dataStore.data
            .map { it[FAVOURITE_APARTMENTS] ?: emptySet() }
            .distinctUntilChanged()

        return when (isConnected) {
            NetworkConnectivityStatus.CONNECTED -> combine(
                favApartmentIdsFlow,
                flowOf(apiService.getAllApartments())
            ) { favApartmentIds, apartments ->
                apartments.onEach { it.isFavourite = favApartmentIds.contains(it.id.toString()) }
                    .filter { it.isFavourite } // Return only favorite apartments
            }

            NetworkConnectivityStatus.NOT_CONNECTED -> combine(
                favApartmentIdsFlow,
                apartmentDao.getAllApartmentsFlow()
                    .map { list -> list.map { it.toApartment() } }
            ) { favApartmentIds, apartments ->
                apartments.onEach { it.isFavourite = favApartmentIds.contains(it.id.toString()) }
                    .filter { it.isFavourite } // Return only favorite apartments
            }
        }
    }


    override suspend fun addApartmentToFavourite(apartmentId: Int) {
        dataStore.edit { preferences ->
            val currentSet = preferences[FAVOURITE_APARTMENTS] ?: emptySet()
            preferences[FAVOURITE_APARTMENTS] = currentSet + apartmentId.toString()
        }
    }

    override suspend fun removeApartmentFromFavourite(apartmentId: Int) {
        dataStore.edit { preferences ->
            val currentSet = preferences[FAVOURITE_APARTMENTS] ?: emptySet()
            preferences[FAVOURITE_APARTMENTS] = currentSet - apartmentId.toString()
        }
    }

}