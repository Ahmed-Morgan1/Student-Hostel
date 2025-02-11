package com.fady.data.repo.base

import com.fady.data.dto.StudentDto

interface IStudentRepo {
    suspend fun getAllStudents(): List<StudentDto>
    suspend fun getStudentById(id: Int): StudentDto
    suspend fun updateStudent(id: Int, student: StudentDto)
    suspend fun deleteStudent(id: Int)
    suspend fun createStudent(student: StudentDto): StudentDto

}