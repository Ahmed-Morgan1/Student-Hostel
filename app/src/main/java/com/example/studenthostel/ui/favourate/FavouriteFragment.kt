package com.example.studenthostel.ui.favourate

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
import com.example.studenthostel.base.BaseAdapter
import com.example.studenthostel.databinding.FavApartmentHolderBinding
import com.example.studenthostel.databinding.FragmentFavourateBinding
import com.example.studenthostel.model.Apartment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouriteFragment : Fragment() {
    private var _binding: FragmentFavourateBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavouriteViewModel by viewModels()
    private lateinit var adapter: BaseAdapter<Apartment, FavApartmentHolderBinding>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavourateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = FavouriteAdaptor()
        binding.rvFavourate.adapter = adapter
        viewModel.event(FavouriteContract.FavouriteEvent.OnFetchApartments)
        observeState()
    }

    private fun observeState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.currentState.collect {
                    val apartments = it.apartments
                    Log.e("wishlist data", apartments.toString())
                    adapter.changeData(apartments)
                }
            }
        }
    }

}