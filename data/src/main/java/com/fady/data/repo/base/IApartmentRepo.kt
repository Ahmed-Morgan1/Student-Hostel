package com.fady.data.repo.base

import com.fady.data.dto.ApartmentDto

interface IApartmentRepo {
    suspend fun getAllApartments(): List<ApartmentDto>
    suspend fun getApartmentById(id: Int): ApartmentDto
}