package com.fady.data.repo.base

import com.fady.data.dto.ApartmentDto
import kotlinx.coroutines.flow.Flow

interface IApartmentRepo {
    suspend fun getAllApartments(): Flow<List<ApartmentDto>>
    suspend fun getApartmentById(id: Int): ApartmentDto


}