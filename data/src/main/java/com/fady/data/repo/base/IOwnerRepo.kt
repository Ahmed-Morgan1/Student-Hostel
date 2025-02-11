package com.fady.data.repo.base

import com.fady.data.dto.OwnerDto

interface IOwnerRepo {
    suspend fun getAllOwners(): List<OwnerDto>
    suspend fun getOwnerById(id: Int): OwnerDto
    suspend fun updateOwner(id: Int, owner: OwnerDto)
    suspend fun deleteOwner(owner: OwnerDto)
    suspend fun createOwner(owner: OwnerDto): OwnerDto
}