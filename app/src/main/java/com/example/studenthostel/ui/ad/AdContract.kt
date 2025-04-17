package com.example.studenthostel.ui.ad

import com.example.studenthostel.base.BaseViewModel

interface AdContract {
    
    data object AdState : BaseViewModel.Status

    sealed class AdEvent : BaseViewModel.Event {
        data object OnNextClick : AdEvent()
    }

    sealed class AdEffect : BaseViewModel.Effect {
        data object NavigateToContact : AdEffect()
    }
    
}