package com.fady.data.dataSoure.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.fady.data.dataSoure.local.relation.OwnerWithApartments
import com.fady.data.dto.OwnerDto

@Dao
interface OwnerDao {

    @Transaction
    @Query("SELECT * FROM Owner")
    suspend fun getAllOwners(): List<OwnerWithApartments>

    @Transaction
    @Query("SELECT * FROM Owner WHERE ownerId = :id")
    suspend fun getOwnerById(id: Int): OwnerWithApartments

    @Transaction
    @Query("SELECT * FROM Owner WHERE ownerId = :id")
    suspend fun getOwnerWithApartments(id: Int): OwnerWithApartments

    @Transaction
    @Query("SELECT * FROM Owner ")
    suspend fun getAllOwnersWithApartments(): List<OwnerWithApartments>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateOwner(owner: OwnerDto)

    @Delete
    suspend fun deleteOwner(owner: OwnerDto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createOwner(owner: OwnerDto)
}