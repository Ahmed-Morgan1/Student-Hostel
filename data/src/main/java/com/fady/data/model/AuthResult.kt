package com.fady.data.model

sealed class AuthResult {
    data object Success: AuthResult()
    data class Error(val message: String) : AuthResult()
}