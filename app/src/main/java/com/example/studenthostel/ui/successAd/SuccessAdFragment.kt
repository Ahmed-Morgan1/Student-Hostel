package com.example.studenthostel.ui.successAd

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.studenthostel.base.BaseFragment
import com.example.studenthostel.base.BaseViewModel
import com.example.studenthostel.databinding.FragmentSucssAdBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SuccessAdFragment : BaseFragment<FragmentSucssAdBinding,
        SuccessAdContract.SuccessAdState,
        SuccessAdContract.SuccessAdEvent,
        SuccessAdContract.SuccessAdEffect>() {
    override val viewModel: BaseViewModel<SuccessAdContract.SuccessAdState, SuccessAdContract.SuccessAdEvent, SuccessAdContract.SuccessAdEffect>
        by viewModels()

    override fun setContent() {
        TODO("Not yet implemented")
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSucssAdBinding = FragmentSucssAdBinding.inflate(inflater, container, false)

    override fun onEffect(viewEffect: SuccessAdContract.SuccessAdEffect) {
        TODO("Not yet implemented")
    }

    override fun onUiStateChange(viewState: SuccessAdContract.SuccessAdState) {
        TODO("Not yet implemented")
    }

}
