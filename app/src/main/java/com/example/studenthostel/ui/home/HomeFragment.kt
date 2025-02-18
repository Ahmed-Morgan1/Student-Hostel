package com.example.studenthostel.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.studenthostel.databinding.FragmentHomeBinding
import com.example.studenthostel.model.Apartment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding?=null
    private val binding: FragmentHomeBinding get () = _binding!!
    private lateinit var adapter: HomeAdaptor


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = HomeAdaptor()
        binding.rvFeaturedSales.adapter = adapter
        binding.rvFeaturedRental.adapter = adapter
        observeState()
        viewModel.event(HomeContract.HomeEvent.OnFetchApartments)


    }

    private fun observeState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.currentState.collect {
                    val apartments = it.apartments.sortedBy {apartment ->
                        apartment.apartmentStatusType == Apartment.ApartmentStatusType.RENT
                    }
                    adapter.changeData(apartments)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}