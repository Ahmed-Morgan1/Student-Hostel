package com.example.studenthostel.ui.reviewDeal

import com.example.studenthostel.base.BaseViewModel

interface ReviewDealContract {

    data object ReviewDealState : BaseViewModel.Status

    sealed class ReviewDealEvent : BaseViewModel.Event

    sealed class ReviewDealEffect : BaseViewModel.Effect
}