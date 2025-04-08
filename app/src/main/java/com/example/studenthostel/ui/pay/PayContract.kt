package com.example.studenthostel.ui.pay

import com.example.studenthostel.base.BaseViewModel

interface PayContract {

    data object PayState : BaseViewModel.Status

    sealed class PayEvent : BaseViewModel.Event

    sealed class PayEffect : BaseViewModel.Effect
}