package com.example.studenthostel.ui.reviewDeal

import com.example.studenthostel.base.BaseViewModel
import com.fady.data.repo.base.IApartmentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

// TODO Note Delete     {private val apartmentRepo: IApartmentRepo}

@HiltViewModel
class ReviewDealViewModel @Inject constructor(
    private val apartmentRepo: IApartmentRepo
): BaseViewModel<
        ReviewDealContract.ReviewDealState,
        ReviewDealContract.ReviewDealEvent,
        ReviewDealContract.ReviewDealEffect>(
    initialState = ReviewDealContract.ReviewDealState
) {
    override fun event(event: ReviewDealContract.ReviewDealEvent) {
        TODO("Not yet implemented")
    }
}