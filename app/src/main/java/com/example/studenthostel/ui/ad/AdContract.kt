package com.example.studenthostel.ui.ad

import com.example.studenthostel.base.BaseViewModel

interface AdContract {
    
    data object AdState : BaseViewModel.Status
    
    sealed class AdEvent : BaseViewModel.Event 
    
    sealed class AdEffect : BaseViewModel.Effect
    
}