package com.fady.data.repo.impl

import com.fady.data.dataSoure.local.prefrances.TokenManager
import com.fady.data.dataSoure.remote.ApiService
import com.fady.data.model.AuthResult
import com.fady.data.model.LoginUser
import com.fady.data.model.SignupUser
import com.fady.data.repo.base.IAuthRepository
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiService: ApiService,
    private val tokenManager: TokenManager
) : IAuthRepository {

    override suspend fun signUp(
        name: String,
        email: String,
        password: String,
        phoneNumber: String,
        accountType: String
    ): AuthResult {
        val signUpUser = SignupUser(
            username = name,
            email = email,
            password = password,
            phoneNumber = phoneNumber,
            role = accountType
        )
        return try {
            val token = apiService.register(signUpUser)
            tokenManager.saveToken(token.second)
            AuthResult.Success
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "An unknown error occurred")
        }
    }

    override suspend fun login(email: String, password: String): AuthResult {
        val loginUser = LoginUser(email, password)
        return try {
            val token = apiService.login(loginUser)
            tokenManager.saveToken(token.second)
            AuthResult.Success
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "An unknown error occurred")
        }

    }
}