package com.example.studenthostel.ui.home

import androidx.lifecycle.viewModelScope
import com.example.studenthostel.base.BaseViewModel
import com.fady.data.repo.impl.ApartmentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class HomeViewModel @Inject constructor(private val repository: ApartmentRepo):
    BaseViewModel<HomeContract.HomeState, HomeContract.HomeEvent, HomeContract.HomeEffect>(
        HomeContract.HomeState()
    ) {

    override fun event(event: HomeContract.HomeEvent) {
        when (event) {
            is HomeContract.HomeEvent.OnApartmentClicked -> sendEffect(HomeContract.HomeEffect.NavigateToDetails(event.apartment))
            HomeContract.HomeEvent.OnClickAllFeaturedSales -> sendEffect(HomeContract.HomeEffect.NavigateToAllSales(state.apartments.filter { it.isRented != true }))
            is HomeContract.HomeEvent.OnSearchQueryChanged -> setState(state.copy(searchQuery = event.query))
            HomeContract.HomeEvent.OnClickALlFeaturedRental -> sendEffect(HomeContract.HomeEffect.NavigateToAllRental(state.apartments.filter { it.isRented == true }))
            HomeContract.HomeEvent.GetApartments -> getApartments()
        }
    }

    private fun getApartments() {
        viewModelScope.launch {
            val Appartments= repository.getAllApartments()
            setState(state.copy(apartments = Appartments))
        }
    }
}