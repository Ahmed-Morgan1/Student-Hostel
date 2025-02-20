package com.example.studenthostel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.studenthostel.databinding.FragmentAdBinding
import com.example.studenthostel.databinding.FragmentContactBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ContactFragment : Fragment() {
    private var _binding: FragmentContactBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnContactnext.setOnClickListener {
            findNavController().navigate(R.id.action_contactFragment_to_sucssAdFragment)

        }

    }
}