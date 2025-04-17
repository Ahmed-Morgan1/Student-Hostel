package com.example.studenthostel.ui.successAd

import com.example.studenthostel.base.BaseViewModel

interface SuccessAdContract {

    data object SuccessAdState: BaseViewModel.Status

    sealed class SuccessAdEvent : BaseViewModel.Event{
        data object OnBackToHomeClicked : SuccessAdEvent()

    }

    sealed class SuccessAdEffect : BaseViewModel.Effect{
        data object NavigateToHome : SuccessAdEffect()
    }
}