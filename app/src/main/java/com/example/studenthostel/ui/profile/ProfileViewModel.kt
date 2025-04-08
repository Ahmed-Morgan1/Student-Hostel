package com.example.studenthostel.ui.profile

import com.example.studenthostel.base.BaseViewModel
import com.fady.data.repo.base.IAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: IAuthRepository
) : BaseViewModel<ProfileContract.ProfileState, ProfileContract.ProfileEvent, ProfileContract.ProfileEffect>(
    ProfileContract.ProfileState.Loading
) {
    override fun event(event: ProfileContract.ProfileEvent) {
        when (event) {
            ProfileContract.ProfileEvent.OnBack -> sendEffect(ProfileContract.ProfileEffect.NavigateToBack)
            ProfileContract.ProfileEvent.OnFetchProfile -> fetchProfile()
            ProfileContract.ProfileEvent.OnProfileImageClick -> uploadLocalImage()
            ProfileContract.ProfileEvent.OnSaveProfileClick -> saveProfile()
        }
    }


    private fun saveProfile() {

    }

    private fun uploadLocalImage() {
        TODO("Not yet implemented")
    }

    private fun fetchProfile() {
        TODO("Not yet implemented")
    }
}