package com.fady.data.dataSoure.remote

import com.fady.data.dto.ApartmentDto
import com.fady.data.dto.CommentDto
import com.fady.data.dto.OwnerDto
import com.fady.data.dto.StudentDto
import com.fady.data.model.LoginUser
import com.fady.data.model.SignupUser
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface ApiService {

    // Apartment Endpoints
    @GET("Apartment/GetAll")
    suspend fun getAllApartments(): List<ApartmentDto>

    @GET("Apartment/{id}")
    suspend fun getApartmentById(@Path("id") id: Int): ApartmentDto

    @PUT("Apartment/{id}")
    suspend fun updateApartment(@Path("id") id: Int, @Body apartment: ApartmentDto)

    @DELETE("Apartment/{id}")
    suspend fun deleteApartment(@Path("id") id: Int)

    @POST("Apartment")
    suspend fun createApartment(@Body apartment: ApartmentDto): ApartmentDto

    // Comment Endpoints
    @POST("Comment")
    suspend fun postComment(@Body comment: CommentDto): CommentDto

    @DELETE("Comment/{id}")
    suspend fun deleteComment(@Path("id") id: Int)

    // Owner Endpoints
    @GET("Owner/GetAll")
    suspend fun getAllOwners(): List<OwnerDto>

    @GET("Owner/{id}")
    suspend fun getOwnerById(@Path("id") id: Int): OwnerDto

    @PUT("Owner/{id}")
    suspend fun updateOwner(@Path("id") id: Int, @Body owner: OwnerDto)

    @DELETE("Owner/{id}")
    suspend fun deleteOwner(@Path("id") id: Int)

    @POST("Owner")
    suspend fun createOwner(@Body owner: OwnerDto): OwnerDto

    // Student Endpoints
    @GET("Student/GetAll")
    suspend fun getAllStudents(): List<StudentDto>

    @GET("Student/{id}")
    suspend fun getStudentById(@Path("id") id: Int): StudentDto

    @PUT("Student/{id}")
    suspend fun updateStudent(@Path("id") id: Int, @Body student: StudentDto)

    @DELETE("Student/{id}")
    suspend fun deleteStudent(@Path("id") id: Int)

    @POST("Student")
    suspend fun createStudent(@Body student: StudentDto): StudentDto

    @POST("auth/register")
    suspend fun register(@Body user: SignupUser): Pair<String, String>

    @POST("auth/login")
    suspend fun login(@Body user: LoginUser): Pair<String, String>

}