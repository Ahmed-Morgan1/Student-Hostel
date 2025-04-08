package com.example.studenthostel.ui.successAd

import android.net.Uri
import com.example.studenthostel.base.BaseViewModel

interface SuccessAdContract {

    data class SuccessAdState(
        val imageUri: Uri? = null
    ) : BaseViewModel.Status

    sealed class SuccessAdEvent : BaseViewModel.Event{
        data object PickImageClicked : SuccessAdEvent()
        data object PermissionGranted : SuccessAdEvent()
        data object PermissionDenied : SuccessAdEvent()
        data class ImagePicked(val uri: Uri) : SuccessAdEvent()
    }

    sealed class SuccessAdEffect : BaseViewModel.Effect{
        data object OpenImagePicker : SuccessAdEffect()
        data object ShowPermissionRationale : SuccessAdEffect()
        data object ShowPermissionDeniedPermanently : SuccessAdEffect()
    }
}