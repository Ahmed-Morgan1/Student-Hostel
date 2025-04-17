package com.example.studenthostel.ui.content_page

import com.example.studenthostel.base.BaseViewModel
import com.example.studenthostel.model.Apartment

interface ContentPageContract {

    data class ContentPageState(
        val isLoading: Boolean = false,
        val apartment: Apartment?=null,
//        val currentImgIndex: Int = 0,
        val error: String? = null
    ):BaseViewModel.Status

    sealed class ContentPageEvent:BaseViewModel.Event{
        data object LoadApartment:ContentPageEvent()
        data object OnBackClicked:ContentPageEvent()
        data object OnSendMassageClicked:ContentPageEvent()
        data object OnViewPhoneClicked:ContentPageEvent()
        data object OnNextImgClicked:ContentPageEvent()
        data object OnPriviesImgClicked:ContentPageEvent()
        data class OnBookNowClicked(val apartmentId:Int):ContentPageEvent()
    }

    sealed class ContentPageEffect:BaseViewModel.Effect{
        data object NavigateBack:ContentPageEffect()
        data class NavigateToBooking(val apartmentId:Int):ContentPageEffect()
    }

}