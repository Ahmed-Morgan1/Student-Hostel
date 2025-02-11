package com.example.studenthostel.ui.home

import com.example.studenthostel.base.BaseViewModel
import com.fady.data.dto.ApartmentDto

interface HomeContract {

    data class HomeState(
        val apartments: List<ApartmentDto> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null,
        val searchQuery: String = ""
    ): BaseViewModel.Status




    sealed interface HomeEvent: BaseViewModel.Event{
        data object OnClickAllFeaturedSales: HomeEvent
        data object OnClickALlFeaturedRental: HomeEvent
        data class OnSearchQueryChanged(val query: String): HomeEvent
        data class OnApartmentClicked(val apartment: ApartmentDto): HomeEvent
        data object GetApartments: HomeEvent
    }

    sealed interface HomeEffect: BaseViewModel.Effect{
        data class NavigateToDetails(val apartment: ApartmentDto): HomeEffect
        data class NavigateToAllSales(val apartments: List<ApartmentDto>): HomeEffect
        data class NavigateToAllRental(val apartments: List<ApartmentDto>): HomeEffect
    }
}