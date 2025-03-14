package com.example.studenthostel.ui.favourate

import com.example.studenthostel.base.BaseViewModel
import com.example.studenthostel.model.Apartment

interface FavouriteContract {


    data class FavouriteState(
        val apartments: List<Apartment> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null
    ): BaseViewModel.Status

    sealed interface FavouriteEvent : BaseViewModel.Event {
        data object OnFetchApartments : FavouriteEvent
        data class OnApartmentClicked(val apartment: Apartment) : FavouriteEvent
        data class OnRemoveApartmentFromFavourites(val apartment: Apartment) : FavouriteEvent
    }

    sealed interface FavouriteEffect : BaseViewModel.Effect {
        data class NavigateToDetails(val apartment: Apartment) : FavouriteEffect
    }

}