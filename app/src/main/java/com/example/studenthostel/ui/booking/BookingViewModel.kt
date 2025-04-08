package com.example.studenthostel.ui.booking

import com.example.studenthostel.base.BaseViewModel
import com.fady.data.repo.base.IApartmentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

// TODO Note Delete     {private val apartmentRepo: IApartmentRepo}


@HiltViewModel
class BookingViewModel @Inject constructor(
    private val apartmentRepo: IApartmentRepo
) : BaseViewModel<BookingContract.BookingState,
        BookingContract.BookingEvent,
        BookingContract.BookingEffect>(
    initialState = BookingContract.BookingState
) {
    override fun event(event: BookingContract.BookingEvent) {
        TODO("Not yet implemented")
    }
}