package com.example.studenthostel.ui.successAd

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.studenthostel.R
import com.example.studenthostel.base.BaseFragment
import com.example.studenthostel.databinding.FragmentSucssAdBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SuccessAdFragment : BaseFragment<FragmentSucssAdBinding,
        SuccessAdContract.SuccessAdState,
        SuccessAdContract.SuccessAdEvent,
        SuccessAdContract.SuccessAdEffect>() {
    override val viewModel by viewModels<SuccessAdViewModel>()

    override fun setContent() {
        binding.btnBackToHome.setOnClickListener {
            viewModel.event(SuccessAdContract.SuccessAdEvent.OnBackToHomeClicked)
        }

    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSucssAdBinding = FragmentSucssAdBinding.inflate(inflater, container, false)

    override fun onEffect(viewEffect: SuccessAdContract.SuccessAdEffect) {
        when (viewEffect) {
            SuccessAdContract.SuccessAdEffect.NavigateToHome -> navController.navigate(R.id.homeFragment)
        }

    }

    override fun onUiStateChange(viewState: SuccessAdContract.SuccessAdState) {

    }

}
