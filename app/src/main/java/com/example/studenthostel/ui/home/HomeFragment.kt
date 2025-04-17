package com.example.studenthostel.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.studenthostel.R
import com.example.studenthostel.base.BaseFragment
import com.example.studenthostel.databinding.FragmentHomeBinding
import com.example.studenthostel.model.Apartment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding,
        HomeContract.HomeState,
        HomeContract.HomeEvent,
        HomeContract.HomeEffect>() {

    override val viewModel by viewModels<HomeViewModel>()

    private lateinit var adapter: HomeAdaptor

    override fun setContent() {
        adapter = HomeAdaptor()
        adapter.onItemClickListener = HomeAdaptor.OnItemClickListener {
            viewModel.event(HomeContract.HomeEvent.OnApartmentClicked(it.id))
        }
        adapter.onAddToFavouritesClickListener = HomeAdaptor.OnItemClickListener {
            viewModel.event(HomeContract.HomeEvent.OnToggleApartmentFromFavourites(it))
        }
        binding.rvFeaturedSales.adapter = adapter
        binding.rvFeaturedRental.adapter = adapter

        viewModel.event(HomeContract.HomeEvent.OnFetchApartments)
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun onEffect(viewEffect: HomeContract.HomeEffect) {
        when (viewEffect) {
            is HomeContract.HomeEffect.NavigateToAllRental -> TODO()
            is HomeContract.HomeEffect.NavigateToAllSales -> TODO()
            is HomeContract.HomeEffect.NavigateToDetails -> {
                if (navController.currentDestination?.id == R.id.homeFragment) {
                    val action = HomeFragmentDirections
                        .actionHomeFragmentToContactPageFragment(viewEffect.apartmentId)
                    navController.navigate(action)
                }
            }
        }
    }

    override fun onUiStateChange(viewState: HomeContract.HomeState) {
        val apartments = viewState.apartments.sortedBy { apartment ->
            apartment.apartmentStatusType == Apartment.ApartmentStatusType.RENT
        }
        adapter.changeData(apartments)
    }
}