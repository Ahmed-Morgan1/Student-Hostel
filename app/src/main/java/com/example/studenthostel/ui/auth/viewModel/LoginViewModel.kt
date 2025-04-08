package com.example.studenthostel.ui.auth.viewModel

import androidx.lifecycle.viewModelScope
import com.example.studenthostel.base.BaseViewModel
import com.example.studenthostel.ui.auth.contract.LoginContract
import com.fady.data.model.AuthResult
import com.fady.data.repo.impl.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel<LoginContract.LoginState, LoginContract.LoginEvent, LoginContract.LoginEffect>(
        LoginContract.LoginState()
    ){


    override fun event(event: LoginContract.LoginEvent) {
        when (event) {
            LoginContract.LoginEvent.OnForgotPasswordClick -> sendEffect(LoginContract.LoginEffect.NavigateToForgotPassword)
            is LoginContract.LoginEvent.OnLogin -> login(event.email, event.password)
            LoginContract.LoginEvent.OnLoginWithGoogleClick -> googleLogin()
            LoginContract.LoginEvent.OnSignUpClick -> sendEffect(LoginContract.LoginEffect.NavigateToSignUp)
            LoginContract.LoginEvent.OnTogglePasswordVisibility -> setState(state.copy(isPasswordVisible = !state.isPasswordVisible))
        }
    }

    private fun login(email: String, password: String) {
        viewModelScope.launch {
            sendEffect(LoginContract.LoginEffect.NavigateToHome)
          when (val result =  authRepository.login(email, password)) {
                is AuthResult.Error -> sendEffect(LoginContract.LoginEffect.ShowError(result.message))
                is AuthResult.Success -> sendEffect(LoginContract.LoginEffect.NavigateToHome)
            }
        }
    }


    private fun googleLogin() {
        TODO("Not yet implemented")
    }
}