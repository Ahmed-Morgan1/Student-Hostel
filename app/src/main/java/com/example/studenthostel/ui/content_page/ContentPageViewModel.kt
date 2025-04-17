package com.example.studenthostel.ui.content_page

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.studenthostel.base.BaseViewModel
import com.example.studenthostel.mapper.toApartment
import com.fady.data.repo.impl.ApartmentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentPageViewModel @Inject constructor(
    private val apartmentRepo: ApartmentRepo,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<ContentPageContract.ContentPageState, ContentPageContract.ContentPageEvent, ContentPageContract.ContentPageEffect>(
    initialState = ContentPageContract.ContentPageState()
) {
    override fun event(event: ContentPageContract.ContentPageEvent) {
        when (event) {
            ContentPageContract.ContentPageEvent.LoadApartment -> getApartment()
            ContentPageContract.ContentPageEvent.OnBackClicked -> sendEffect(ContentPageContract.ContentPageEffect.NavigateBack)
            is ContentPageContract.ContentPageEvent.OnBookNowClicked -> sendEffect(ContentPageContract.ContentPageEffect.NavigateToBooking(event.apartmentId))
            ContentPageContract.ContentPageEvent.OnNextImgClicked -> TODO()
            ContentPageContract.ContentPageEvent.OnPriviesImgClicked -> TODO()
            ContentPageContract.ContentPageEvent.OnSendMassageClicked -> TODO()
            ContentPageContract.ContentPageEvent.OnViewPhoneClicked -> TODO()
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