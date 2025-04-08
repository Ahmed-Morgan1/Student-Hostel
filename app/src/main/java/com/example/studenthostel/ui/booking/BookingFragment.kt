package com.example.studenthostel.ui.booking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.studenthostel.base.BaseFragment
import com.example.studenthostel.base.BaseViewModel
import com.example.studenthostel.databinding.BookingFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookingFragment: BaseFragment<BookingFragmentBinding,
        BookingContract.BookingState,
        BookingContract.BookingEvent,
        BookingContract.BookingEffect>() {
    override val viewModel: BaseViewModel<BookingContract.BookingState, BookingContract.BookingEvent, BookingContract.BookingEffect>
        by viewModels()

    override fun setContent() {
        TODO("Not yet implemented")
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): BookingFragmentBinding = BookingFragmentBinding.inflate(inflater, container, false)

    override fun onUiStateChange(viewState: BookingContract.BookingState) {
        TODO("Not yet implemented")
    }

    override fun onEffect(viewEffect: BookingContract.BookingEffect) {
        TODO("Not yet implemented")
    }
}