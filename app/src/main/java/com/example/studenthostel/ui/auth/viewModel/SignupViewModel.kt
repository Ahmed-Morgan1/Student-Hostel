package com.example.studenthostel.ui.auth.viewModel

import androidx.lifecycle.viewModelScope
import com.example.studenthostel.base.BaseViewModel
import com.example.studenthostel.ui.auth.contract.SignUpContract
import com.fady.data.model.AuthResult
import com.fady.data.repo.base.IAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val repo: IAuthRepository
) : BaseViewModel<
        SignUpContract.SignUpState,
        SignUpContract.SignUpEvent,
        SignUpContract.SignUpEffect
        >(
    initialState = SignUpContract.SignUpState()
){
    override fun event(event: SignUpContract.SignUpEvent) {
        when (event) {
            is SignUpContract.SignUpEvent.OnAccountTypeSelected -> setState(state.copy(accountType = event.accountType))
            SignUpContract.SignUpEvent.OnLoginClick -> sendEffect(SignUpContract.SignUpEffect.NavigateToLogin)
            is SignUpContract.SignUpEvent.OnSignUp -> signUp(event.name, event.email, event.password, event.confirmPassword, event.accountType)
            SignUpContract.SignUpEvent.OnTogglePasswordVisibility -> setState(state.copy(isPasswordVisible = !state.isPasswordVisible))
        }
    }

    private fun signUp(
        name: String,
        email: String,
        password: String,
        phoneNumber: String,
        accountType: String
    ) {
        viewModelScope.launch {
          val result =  repo.signUp(name, email, password, phoneNumber, accountType)
            when (result) {
                is AuthResult.Error -> {}
                AuthResult.Success -> sendEffect(SignUpContract.SignUpEffect.NavigateToHome)
            }
        }
    }
}