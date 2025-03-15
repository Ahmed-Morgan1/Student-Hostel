package com.example.studenthostel.ui.auth.contract

import com.example.studenthostel.base.BaseViewModel

interface LoginContract {

    data class LoginState(
        val email: String = "",
        val password: String = "",
        val isPasswordVisible: Boolean = true
    ) : BaseViewModel.Status

    sealed interface LoginEvent : BaseViewModel.Event {
        data class OnLogin(val email: String, val password: String) : LoginEvent
        data object OnSignUpClick : LoginEvent
        data object OnForgotPasswordClick : LoginEvent
        data object OnLoginWithGoogleClick : LoginEvent
        data object OnTogglePasswordVisibility : LoginEvent
    }

    sealed interface LoginEffect : BaseViewModel.Effect {
        data class ShowError(val message: String) : LoginEffect
        data object NavigateToHome : LoginEffect
        data object NavigateToSignUp : LoginEffect
        data object NavigateToForgotPassword : LoginEffect
    }

}