package com.example.studenthostel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.studenthostel.databinding.FragmentAdBinding
import com.example.studenthostel.databinding.FragmentSucssAdBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SucssAdFragment : Fragment() {
    private var _binding: FragmentSucssAdBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSucssAdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBackToHome.setOnClickListener {
            findNavController().navigate(R.id.action_sucssAdFragment_to_homeFragment)

        }

    }
}