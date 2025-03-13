package com.example.studenthostel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.studenthostel.databinding.FragmentAdBinding
import com.example.studenthostel.databinding.FragmentSignupBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AdFragment : Fragment() {

    private var _binding: FragmentAdBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAdnext.setOnClickListener {
            findNavController().navigate(R.id.action_adFragment_to_contactFragment)

        }

    }
}