package com.example.studenthostel.ui.ad

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.studenthostel.base.BaseFragment
import com.example.studenthostel.base.BaseViewModel
import com.example.studenthostel.databinding.FragmentAdBinding
import com.example.studenthostel.utils.requestImagePermission
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AdFragment : BaseFragment<FragmentAdBinding,
        AdContract.AdState,
        AdContract.AdEvent,
        AdContract.AdEffect>() {
    override val viewModel: BaseViewModel<AdContract.AdState, AdContract.AdEvent, AdContract.AdEffect>
            by viewModels<AdContractViewModel>()


    override fun setContent() {
        requestImagePermission {
            Log.e("TAG", "Premistion granted")
        }
    }

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentAdBinding =
        FragmentAdBinding.inflate(inflater, container, false)

    override fun onEffect(viewEffect: AdContract.AdEffect) {
        // NOT implemented
    }

    override fun onUiStateChange(viewState: AdContract.AdState) {
        // NOT implemented
    }
}