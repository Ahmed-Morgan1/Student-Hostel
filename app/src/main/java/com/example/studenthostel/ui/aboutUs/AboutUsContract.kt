package com.example.studenthostel.ui.aboutUs

import com.example.studenthostel.base.BaseViewModel

interface AboutUsContract {

    data object AboutUsState : BaseViewModel.Status

    sealed class AboutUsEvent : BaseViewModel.Event

    sealed class AboutUsEffect : BaseViewModel.Effect

}