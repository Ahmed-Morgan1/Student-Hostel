package com.example.studenthostel.ui.aboutUs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.studenthostel.base.BaseFragment
import com.example.studenthostel.base.BaseViewModel
import com.example.studenthostel.databinding.FragmentAboutUs2Binding


class AboutUsFragment : BaseFragment<FragmentAboutUs2Binding,
        AboutUsContract.AboutUsState,
        AboutUsContract.AboutUsEvent,
        AboutUsContract.AboutUsEffect>()  {
    override val viewModel: BaseViewModel<AboutUsContract.AboutUsState, AboutUsContract.AboutUsEvent, AboutUsContract.AboutUsEffect>
        by viewModels()

    override fun setContent() {
        TODO("Not yet implemented")
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAboutUs2Binding = FragmentAboutUs2Binding.inflate(inflater, container, false)

    override fun onEffect(viewEffect: AboutUsContract.AboutUsEffect) {
        TODO("Not yet implemented")
    }

    override fun onUiStateChange(viewState: AboutUsContract.AboutUsState) {
        TODO("Not yet implemented")
    }

}