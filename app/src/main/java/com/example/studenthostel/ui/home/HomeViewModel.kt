package com.example.studenthostel.ui.home

import com.example.studenthostel.base.BaseViewModel



class HomeViewModel :
    BaseViewModel<HomeContract.HomeState, HomeContract.HomeEvent, HomeContract.HomeEffect>(
        HomeContract.HomeState()
    ) {

    override fun event(event: HomeContract.HomeEvent) {
        when (event) {
            is HomeContract.HomeEvent.OnApartmentClicked -> sendEffect(HomeContract.HomeEffect.NavigateToDetails(event.apartment))
            HomeContract.HomeEvent.OnClickAllFeaturedSales -> sendEffect(HomeContract.HomeEffect.NavigateToAllSales(state.apartments.filter { it.isRented != true }))
            is HomeContract.HomeEvent.OnSearchQueryChanged -> setState(state.copy(searchQuery = event.query))
            HomeContract.HomeEvent.OnClickALlFeaturedRental -> sendEffect(HomeContract.HomeEffect.NavigateToAllRental(state.apartments.filter { it.isRented == true }))
        }
    }
}