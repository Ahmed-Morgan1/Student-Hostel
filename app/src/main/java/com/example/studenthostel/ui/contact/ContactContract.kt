package com.example.studenthostel.ui.contact

import com.example.studenthostel.base.BaseViewModel

interface ContactContract {

    data object ContactState:BaseViewModel.Status


    sealed class ContactEvent :BaseViewModel.Event{
        data class OnNextClicked(val isEmail: Boolean, val isSms: Boolean) : ContactEvent()
    }

    sealed class ContactEffect :BaseViewModel.Effect{
        data object NavigateToSuccessAd : ContactEffect()
    }

}