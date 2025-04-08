package com.example.studenthostel.ui.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.studenthostel.R
import com.example.studenthostel.base.BaseFragment
import com.example.studenthostel.base.BaseViewModel
import com.example.studenthostel.databinding.FragmentContactBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ContactFragment : BaseFragment<FragmentContactBinding,
        ContactContract.ContactState,
        ContactContract.ContactEvent,
        ContactContract.ContactEffect>() {
    override val viewModel: BaseViewModel<
            ContactContract.ContactState,
            ContactContract.ContactEvent,
            ContactContract.ContactEffect> by viewModels()

    override fun setContent() {
        binding.btnContactnext.setOnClickListener {
            val isEmail = binding.checkBoxEmail.isChecked
            val isSms = binding.checkBoxSms.isChecked
            viewModel.event(ContactContract.ContactEvent.OnNextClicked(isEmail, isSms))
        }
    }


    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentContactBinding = FragmentContactBinding.inflate(inflater, container, false)

    override fun onEffect(viewEffect: ContactContract.ContactEffect) {
        when (viewEffect) {
            ContactContract.ContactEffect.NavigateToBooking -> navController.navigate(R.id.bookingFragment)
        }
    }

    override fun onUiStateChange(viewState: ContactContract.ContactState) = Unit

}