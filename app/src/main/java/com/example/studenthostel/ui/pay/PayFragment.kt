package com.example.studenthostel.ui.pay

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.studenthostel.base.BaseFragment
import com.example.studenthostel.base.BaseViewModel
import com.example.studenthostel.databinding.FragmentPayBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PayFragment : BaseFragment<FragmentPayBinding,
        PayContract.PayState,
        PayContract.PayEvent,
        PayContract.PayEffect>() {
    override val viewModel: BaseViewModel<PayContract.PayState, PayContract.PayEvent, PayContract.PayEffect>
            by viewModels()

    override fun setContent() {
        TODO("Not yet implemented")
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPayBinding = FragmentPayBinding.inflate(inflater, container, false)

    override fun onEffect(viewEffect: PayContract.PayEffect) {
        TODO("Not yet implemented")
    }

    override fun onUiStateChange(viewState: PayContract.PayState) {
        TODO("Not yet implemented")
    }

}