package com.example.studenthostel.ui.booking

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.studenthostel.base.BaseViewModel
import com.example.studenthostel.mapper.toApartment
import com.fady.data.repo.base.IApartmentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(
    private val apartmentRepo: IApartmentRepo,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<BookingContract.BookingState,
        BookingContract.BookingEvent,
        BookingContract.BookingEffect>(
    initialState = BookingContract.BookingState()
) {
    override fun event(event: BookingContract.BookingEvent) {
        when (event) {
            BookingContract.BookingEvent.LoadApartment -> getApartment()
        }
    }

    private fun getApartment() {
        setState(state.copy(isLoading = true))
        viewModelScope.launch {
            val id = getApartmentId()
            val apartment = apartmentRepo.getApartmentById(id).toApartment()
            setState(state.copy(apartment = apartment))
        }
    }

    private fun getApartmentId(): Int = checkNotNull(savedStateHandle.get<Int>("Apartment_id"))
}