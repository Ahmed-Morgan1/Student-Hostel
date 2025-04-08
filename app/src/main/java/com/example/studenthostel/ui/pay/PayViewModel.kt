package com.example.studenthostel.ui.pay

import com.example.studenthostel.base.BaseViewModel
import com.fady.data.repo.base.IApartmentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

// TODO Note Delete     {private val apartmentRepo: IApartmentRepo}


@HiltViewModel
class PayViewModel @Inject constructor(
    private val apartmentRepo: IApartmentRepo
): BaseViewModel<PayContract.PayState,
        PayContract.PayEvent,
        PayContract.PayEffect>(
    initialState = PayContract.PayState
) {

    override fun event(event: PayContract.PayEvent) {
        TODO("Not yet implemented")
    }
}