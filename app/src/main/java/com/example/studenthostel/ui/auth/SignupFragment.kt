package com.example.studenthostel.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.studenthostel.R
import com.example.studenthostel.databinding.FragmentSignupBinding
import com.example.studenthostel.ui.auth.contract.SignUpContract
import com.example.studenthostel.ui.auth.viewModel.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignupFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SignupViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // بيانات الاقتراحات لـ AutoCompleteTextView
        val items = arrayOf("Student", "Real Estate Marketer")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, items)

        // ربط AutoCompleteTextView
        val autoCompleteTextView: AutoCompleteTextView = binding.autoCompleteTextView
        autoCompleteTextView.setAdapter(adapter)

        // إعداد المستمع عند اختيار عنصر
        autoCompleteTextView.setOnItemClickListener { adapterView, _, position, _ ->
            val item = adapterView.getItemAtPosition(position).toString()
            viewModel.event(SignUpContract.SignUpEvent.OnAccountTypeSelected(item))
            Toast.makeText(requireContext(), "تم اختيار: $item", Toast.LENGTH_SHORT).show()
        }

        // إعداد زر التسجيل
        binding.btnSignup.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment2_to_homeFragment)
        }

        // إعداد التنقل إلى تسجيل الدخول
        binding.LoginText.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment2_to_loginFragment)
        }
    }

    private fun observeStateViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.currentState.collect { state ->
                    binding.etName.setText(state.name)
                    binding.etEmail.setText(state.email)
                    binding.etPassword.setText(state.password)
                    binding.etRepassword.setText(state.confirmPassword)
                    binding.autoCompleteTextView.setText(state.accountType)

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
