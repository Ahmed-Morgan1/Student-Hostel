package com.fady.data.repo.base

import com.fady.data.dto.ApartmentDto
import kotlinx.coroutines.flow.Flow

interface IFavouriteApartmentRepo {

    suspend fun getAllFavouriteApartments(): Flow<List<ApartmentDto>>
    suspend fun addApartmentToFavourite(apartmentId: Int)
    suspend fun removeApartmentFromFavourite(apartmentId: Int)
}