package com.example.studenthostel.ui.booking

import com.example.studenthostel.base.BaseViewModel
import com.example.studenthostel.model.Apartment

interface BookingContract {

    data class BookingState(
        val isLoading: Boolean = false,
        val apartment: Apartment? = null,
        val error: String? = null
    ) : BaseViewModel.Status

    sealed interface BookingEvent : BaseViewModel.Event{
        data object LoadApartment : BookingEvent
    }

    sealed interface BookingEffect : BaseViewModel.Effect


}