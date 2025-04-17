package com.example.studenthostel.ui.ad

import com.example.studenthostel.base.BaseViewModel
import com.fady.data.repo.base.IApartmentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


// TODO Note Delete     {private val apartmentRepo: IApartmentRepo}

@HiltViewModel
class AdContractViewModel @Inject constructor(
    private val apartmentRepo: IApartmentRepo
): BaseViewModel<
        AdContract.AdState,
        AdContract.AdEvent,
        AdContract.AdEffect>(
    AdContract.AdState
) {
    override fun event(event: AdContract.AdEvent) {
        when (event) {
            AdContract.AdEvent.OnNextClick -> sendEffect(AdContract.AdEffect.NavigateToContact)
        }
    }
}