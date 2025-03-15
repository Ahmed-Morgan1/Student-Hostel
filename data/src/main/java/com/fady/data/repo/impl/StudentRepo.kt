package com.fady.data.repo.impl

import com.fady.data.dataSoure.local.dataBase.dao.StudentDao
import com.fady.data.dataSoure.remote.ApiService
import com.fady.data.dto.StudentDto
import com.fady.data.networkConnectivity.IConnectivityHandler
import com.fady.data.networkConnectivity.NetworkConnectivityStatus
import com.fady.data.repo.base.IStudentRepo
import jakarta.inject.Inject

class StudentRepo @Inject constructor(
    private val studentDao: StudentDao,
    private val connectivityHandler: IConnectivityHandler,
    private val apiService: ApiService
) : IStudentRepo {
    override suspend fun getAllStudents(): List<StudentDto> =
        when (connectivityHandler.isNetworkAvailable()) {
            NetworkConnectivityStatus.CONNECTED -> apiService.getAllStudents()
            NetworkConnectivityStatus.NOT_CONNECTED -> studentDao.getAllStudents().map {
                it.toStudent()
            }
        }

    override suspend fun getStudentById(id: Int): StudentDto =
        when (connectivityHandler.isNetworkAvailable()) {
            NetworkConnectivityStatus.CONNECTED -> apiService.getStudentById(id)
            NetworkConnectivityStatus.NOT_CONNECTED -> studentDao.getStudentById(id).toStudent()
        }

    override suspend fun updateStudent(id: Int, student: StudentDto) {
        apiService.updateStudent(id, student)
        studentDao.updateStudentApartment(student)
    }


    override suspend fun deleteStudent(id: Int) = apiService.deleteStudent(id)
    override suspend fun createStudent(student: StudentDto): StudentDto =
        apiService.createStudent(student)
}


