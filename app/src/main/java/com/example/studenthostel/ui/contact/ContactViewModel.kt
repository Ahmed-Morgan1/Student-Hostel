package com.example.studenthostel.ui.contact

import com.example.studenthostel.base.BaseViewModel
import com.fady.data.repo.base.IApartmentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
// TODO Note Delete     {private val apartmentRepo: IApartmentRepo}

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val apartmentRepo: IApartmentRepo
) : BaseViewModel<
        ContactContract.ContactState,
        ContactContract.ContactEvent,
        ContactContract.ContactEffect>(
    initialState = ContactContract.ContactState
) {
    override fun event(event: ContactContract.ContactEvent) {
        when (event) {
            is ContactContract.ContactEvent.OnNextClicked -> sendEffect(ContactContract.ContactEffect.NavigateToSuccessAd)
        }
    }
}