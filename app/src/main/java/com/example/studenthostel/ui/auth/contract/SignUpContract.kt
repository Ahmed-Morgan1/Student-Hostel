package com.example.studenthostel.ui.auth.contract

import com.example.studenthostel.base.BaseViewModel

interface SignUpContract {

    data class SignUpState(
        val name: String = "",
        val email: String = "",
        val password: String = "",
        val confirmPassword: String = "",
        val isPasswordVisible: Boolean = true,
        val accountType: String = ""
    ): BaseViewModel.Status

    sealed interface SignUpEvent: BaseViewModel.Event {
        data class OnSignUp(val name: String, val email: String, val password: String, val confirmPassword: String, val accountType: String) : SignUpEvent
        data object OnLoginClick : SignUpEvent
        data object OnTogglePasswordVisibility : SignUpEvent
        data class OnAccountTypeSelected(val accountType: String) : SignUpEvent
    }

    sealed interface SignUpEffect : BaseViewModel.Effect {
        data object NavigateToHome : SignUpEffect
        data object NavigateToLogin : SignUpEffect
    }
}