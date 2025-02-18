package com.fady.data.repo.impl

import com.fady.data.dataSoure.local.dao.ApartmentDao
import com.fady.data.dataSoure.local.dao.OwnerDao
import com.fady.data.dataSoure.remote.ApiService
import com.fady.data.dto.ApartmentDto
import com.fady.data.networkConnectivity.IConnectivityHandler
import com.fady.data.networkConnectivity.NetworkConnectivityStatus
import com.fady.data.repo.base.IApartmentRepo
import jakarta.inject.Inject

class ApartmentRepo @Inject constructor(
    private val apartmentDao: ApartmentDao,
    private val ownerDao: OwnerDao,
    private val connectivityHandler: IConnectivityHandler,
    private val apiService: ApiService
) : IApartmentRepo {

    private val isNetworkConnected = connectivityHandler.isNetworkAvailable()

    override suspend fun getAllApartments(): List<ApartmentDto> =
        when (isNetworkConnected) {
            NetworkConnectivityStatus.CONNECTED -> {
               val apartments = apiService.getAllApartments()
                saveApartmentsLocally(apartments)
                apartments
            }
            NetworkConnectivityStatus.NOT_CONNECTED -> apartmentDao.getAllApartments().map {
                it.toApartment()
            }
        }

    override suspend fun getApartmentById(id: Int): ApartmentDto =
        when (isNetworkConnected) {
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