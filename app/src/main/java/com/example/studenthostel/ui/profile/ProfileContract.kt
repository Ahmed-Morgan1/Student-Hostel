package com.example.studenthostel.ui.profile

import com.example.studenthostel.base.BaseViewModel

interface ProfileContract {

    sealed class ProfileState : BaseViewModel.Status {
        data object Loading : ProfileState()
        data object ProfileFetched : ProfileState() }

    sealed interface ProfileEvent : BaseViewModel.Event {
        data object OnBack : ProfileEvent
        data object OnFetchProfile : ProfileEvent
        data object OnSaveProfileClick : ProfileEvent
        data object OnProfileImageClick: ProfileEvent
    }

    sealed interface ProfileEffect : BaseViewModel.Effect{
        data object NavigateToBack : ProfileEffect
    }


}