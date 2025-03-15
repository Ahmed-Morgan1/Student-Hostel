package com.fady.data.repo.base

import com.fady.data.model.AuthResult

interface IAuthRepository {

    suspend fun signUp(name: String, email: String, password: String, confirmPassword: String, accountType: String): AuthResult

    suspend fun login(email: String, password: String): AuthResult

}