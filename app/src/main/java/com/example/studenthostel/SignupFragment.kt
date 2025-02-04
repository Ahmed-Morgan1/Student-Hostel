package com.example.studenthostel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.studenthostel.databinding.FragmentLoginBinding
import com.example.studenthostel.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!! // Getter لربط العناصر

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // إنشاء الربط (binding)
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // إعداد زر Login للتنقل إلى HomeFragment
        binding.btnSignup.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment2_to_homeFragment)
        }

        // إعداد نص Sign Up للتنقل إلى SignUpFragment
        binding.LoginText.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment2_to_loginFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // إلغاء الربط لتجنب تسريبات الذاكرة
    }
}