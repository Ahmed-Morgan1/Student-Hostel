package com.fady.data.repo.impl

import com.fady.data.dataSoure.local.dataBase.dao.OwnerDao
import com.fady.data.dataSoure.remote.ApiService
import com.fady.data.dto.OwnerDto
import com.fady.data.networkConnectivity.IConnectivityHandler
import com.fady.data.networkConnectivity.NetworkConnectivityStatus
import com.fady.data.repo.base.IOwnerRepo
import jakarta.inject.Inject

class OwnerRepo @Inject constructor(
    private val ownerDao: OwnerDao,
    private val apiService: ApiService,
    connectivityHandler: IConnectivityHandler,
) : IOwnerRepo {
    private val isNetworkConnected = connectivityHandler.isNetworkAvailable()

    override suspend fun getAllOwners(): List<OwnerDto> = when (isNetworkConnected) {
        NetworkConnectivityStatus.CONNECTED -> apiService.getAllOwners()
        NetworkConnectivityStatus.NOT_CONNECTED -> ownerDao.getAllOwners().map {
            it.toOwner()
        }
    }

    override suspend fun getOwnerById(id: Int): OwnerDto = when (isNetworkConnected) {
        NetworkConnectivityStatus.CONNECTED -> apiService.getOwnerById(id)
        NetworkConnectivityStatus.NOT_CONNECTED -> ownerDao.getOwnerById(id).toOwner()
    }

    override suspend fun updateOwner(id: Int, owner: OwnerDto) {
        if (isNetworkConnected == NetworkConnectivityStatus.CONNECTED) {
            apiService.updateOwner(id, owner)
            ownerDao.updateOwner(owner)
        } else
            ownerDao.updateOwner(owner)

    }

    override suspend fun deleteOwner(owner: OwnerDto) {
        if (isNetworkConnected == NetworkConnectivityStatus.CONNECTED) {
            apiService.deleteOwner(owner.id)
            ownerDao.deleteOwner(owner)
        } else
            ownerDao.deleteOwner(owner)

    }

    override suspend fun createOwner(owner: OwnerDto): OwnerDto  {
        if (isNetworkConnected == NetworkConnectivityStatus.CONNECTED) {
            val ownerDto = apiService.createOwner(owner)
            ownerDao.createOwner(ownerDto)
            return ownerDto
        }
        return owner
    }
}