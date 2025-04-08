package com.example.studenthostel.ui.aboutUs

import com.example.studenthostel.base.BaseViewModel
import com.fady.data.repo.base.IApartmentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

// TODO Note Delete     {private val apartmentRepo: IApartmentRepo}


@HiltViewModel
class AboutUsViewModel @Inject constructor (
    private val apartmentRepo: IApartmentRepo
): BaseViewModel<
        AboutUsContract.AboutUsState,
        AboutUsContract.AboutUsEvent,
        AboutUsContract.AboutUsEffect>(
    initialState = AboutUsContract.AboutUsState
) {
    override fun event(event: AboutUsContract.AboutUsEvent) {
        TODO("Not yet implemented")
    }
}