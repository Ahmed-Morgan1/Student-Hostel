package com.example.studenthostel.ui.reviewDeal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.studenthostel.base.BaseFragment
import com.example.studenthostel.base.BaseViewModel
import com.example.studenthostel.databinding.FragmentReviewMyDealBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewMyDealFragment : BaseFragment<FragmentReviewMyDealBinding,
        ReviewDealContract.ReviewDealState,
        ReviewDealContract.ReviewDealEvent,
        ReviewDealContract.ReviewDealEffect>() {
    override val viewModel: BaseViewModel<ReviewDealContract.ReviewDealState, ReviewDealContract.ReviewDealEvent, ReviewDealContract.ReviewDealEffect>
       by viewModels()

    override fun setContent() {
        TODO("Not yet implemented")
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentReviewMyDealBinding = FragmentReviewMyDealBinding.inflate(inflater, container, false)

    override fun onUiStateChange(viewState: ReviewDealContract.ReviewDealState) {
        TODO("Not yet implemented")
    }

    override fun onEffect(viewEffect: ReviewDealContract.ReviewDealEffect) {
        TODO("Not yet implemented")
    }
}