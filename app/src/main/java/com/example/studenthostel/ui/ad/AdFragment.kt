package com.example.studenthostel.ui.ad

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.studenthostel.R
import com.example.studenthostel.base.BaseFragment
import com.example.studenthostel.databinding.FragmentAdBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AdFragment : BaseFragment<FragmentAdBinding,
        AdContract.AdState,
        AdContract.AdEvent,
        AdContract.AdEffect>() {

    override val viewModel by viewModels<AdContractViewModel>()


    override fun setContent() {
        binding.btnAdnext.setOnClickListener {
            viewModel.event(AdContract.AdEvent.OnNextClick)
        }
    }

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentAdBinding =
        FragmentAdBinding.inflate(inflater, container, false)

    override fun onEffect(viewEffect: AdContract.AdEffect) {
        when (viewEffect) {
            AdContract.AdEffect.NavigateToContact -> {
                if (navController.currentDestination?.id == R.id.adFragment) {
                    val action = AdFragmentDirections.actionAdFragmentToContactFragment()
                    navController.navigate(action)
                }
            }
        }
    }

    override fun onUiStateChange(viewState: AdContract.AdState) {
        // NOT implemented
    }
}