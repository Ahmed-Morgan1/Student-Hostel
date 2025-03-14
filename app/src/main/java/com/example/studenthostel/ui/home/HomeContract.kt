package com.example.studenthostel.ui.home

import com.example.studenthostel.base.BaseViewModel
import com.example.studenthostel.model.Apartment

interface HomeContract {

    data class HomeState(
        val apartments: List<Apartment> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null,
        val searchQuery: String = ""
    ): BaseViewModel.Status




    sealed interface HomeEvent: BaseViewModel.Event{
        data object OnFetchApartments: HomeEvent
        data object OnClickAllSales: HomeEvent
        data object OnClickAllRental: HomeEvent
        data class OnClickApartment(val apartment: Apartment): HomeEvent
        data object OnClickAllFeaturedSales: HomeEvent
        data object OnClickALlFeaturedRental: HomeEvent
        data class OnSearchQueryChanged(val query: String): HomeEvent
        data class OnApartmentClicked(val apartment: Apartment): HomeEvent
        data class OnToggleApartmentFromFavourites(val apartment: Apartment): HomeEvent

    }

    sealed interface HomeEffect: BaseViewModel.Effect{
        data class NavigateToDetails(val apartment: Apartment): HomeEffect
        data class NavigateToAllSales(val apartments: List<Apartment>): HomeEffect
        data class NavigateToAllRental(val apartments: List<Apartment>): HomeEffect
    }
}