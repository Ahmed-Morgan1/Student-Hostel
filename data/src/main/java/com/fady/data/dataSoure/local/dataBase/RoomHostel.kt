package com.fady.data.dataSoure.local.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fady.data.dataSoure.local.dataBase.dao.ApartmentDao
import com.fady.data.dataSoure.local.dataBase.dao.CommentDao
import com.fady.data.dataSoure.local.dataBase.dao.OwnerDao
import com.fady.data.dataSoure.local.dataBase.dao.StudentDao
import com.fady.data.dto.ApartmentDto
import com.fady.data.dto.CommentDto
import com.fady.data.dto.OwnerDto
import com.fady.data.dto.StudentDto

@Database(
    entities = [
        ApartmentDto::class,
        CommentDto::class,
        OwnerDto::class,
        StudentDto::class
    ],
    version = 1,
    exportSchema = false
)
abstract class RoomHostel : RoomDatabase() {
    abstract val apartmentDao: ApartmentDao
    abstract val commentDao: CommentDao
    abstract val ownerDao: OwnerDao
    abstract val studentDao: StudentDao
}