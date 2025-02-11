package com.fady.data.dataSoure.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.fady.data.dataSoure.local.relation.StudentWithApartment
import com.fady.data.dto.StudentDto

@Dao
interface StudentDao {

    @Transaction
    @Query("SELECT * FROM Student")
    suspend fun getAllStudents(): List<StudentWithApartment>

    @Transaction
    @Query("SELECT * FROM Student WHERE studentId = :id")
    suspend fun getStudentById(id: Int): StudentWithApartment

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createStudent(student: StudentDto)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateStudentApartment(student: StudentDto)

    @Delete
    suspend fun deleteStudent(student: StudentDto)

}