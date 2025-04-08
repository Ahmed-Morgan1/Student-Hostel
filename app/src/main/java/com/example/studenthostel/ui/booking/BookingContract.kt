package com.example.studenthostel.ui.booking

import com.example.studenthostel.base.BaseViewModel

interface BookingContract {

    data object BookingState : BaseViewModel.Status

    sealed interface BookingEvent : BaseViewModel.Event

    sealed interface BookingEffect : BaseViewModel.Effect


}