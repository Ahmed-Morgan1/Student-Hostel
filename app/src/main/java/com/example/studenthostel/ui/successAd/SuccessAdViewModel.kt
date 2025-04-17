package com.example.studenthostel.ui.successAd

import com.example.studenthostel.base.BaseViewModel
import com.fady.data.repo.base.IApartmentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

// TODO Note Delete     {private val apartmentRepo: IApartmentRepo}


@HiltViewModel
class SuccessAdViewModel @Inject constructor(
    private val apartmentRepo: IApartmentRepo
) : BaseViewModel<
        SuccessAdContract.SuccessAdState,
        SuccessAdContract.SuccessAdEvent,
        SuccessAdContract.SuccessAdEffect>(
    initialState = SuccessAdContract.SuccessAdState
) {
    override fun event(event: SuccessAdContract.SuccessAdEvent) {
        when (event) {
            SuccessAdContract.SuccessAdEvent.OnBackToHomeClicked -> sendEffect(SuccessAdContract.SuccessAdEffect.NavigateToHome)
        }
    }

}