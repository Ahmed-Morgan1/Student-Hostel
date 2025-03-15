package com.example.studenthostel.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.studenthostel.R
import com.example.studenthostel.databinding.FragmentLoginBinding
import com.example.studenthostel.ui.auth.contract.LoginContract
import com.example.studenthostel.ui.auth.viewModel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeStateViewModel()
        observeEffectViewModel()

        binding.btnLogin.setOnClickListener {
            viewModel.event(
                LoginContract.LoginEvent.OnLogin(
                    binding.etEmail.text.toString(), binding.etPassword.text.toString()
                )
            )
        }

        binding.signupText.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment2)
        }
    }

    private fun observeEffectViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effect.collect { loginEffect ->
                    Log.e("TAG_loginEffect", loginEffect.toString() )
                    when (loginEffect) {
                        is LoginContract.LoginEffect.NavigateToHome -> findNavController().navigate(
                            R.id.action_loginFragment_to_homeFragment
                        )


                        LoginContract.LoginEffect.NavigateToForgotPassword -> {}

                        is LoginContract.LoginEffect.ShowError -> {}
                        LoginContract.LoginEffect.NavigateToSignUp -> findNavController().navigate(
                            R.id.action_loginFragment_to_signupFragment2
                        )
                    }
                }
            }
        }
    }

    private fun observeStateViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.currentState.collect { loginState ->
                    binding.etEmail.setText(loginState.email)
                    binding.etPassword.setText(loginState.password)
                }
            }
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
