package com.fady.data.dataSoure.local.dataBase.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import com.fady.data.dataSoure.local.dataBase.relation.ApartmentWithComment
import com.fady.data.dto.ApartmentDto
import kotlinx.coroutines.flow.Flow


@Dao
interface ApartmentDao {

    @Transaction
    @Query("SELECT * FROM Apartment")
    suspend fun getAllApartments(): List<ApartmentWithComment>

    @Transaction
    @Query("SELECT * FROM Apartment")
    fun getAllApartmentsFlow(): Flow<List<ApartmentWithComment>>

    @Transaction
    @Query("SELECT * FROM Apartment WHERE id = :id")
    suspend fun getApartmentById(id: Int): ApartmentWithComment

    @Transaction
    @Query("SELECT * FROM Apartment WHERE ownerId = :id")
    suspend fun getApartmentByOwnerId(id: Int): List<ApartmentWithComment>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateApartment(apartment: ApartmentDto)

    @Delete
    suspend fun deleteApartment(apartment: ApartmentDto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createApartment(apartment: ApartmentDto)


    @Upsert
    suspend fun upsertApartment(apartments: List<ApartmentDto>)


}