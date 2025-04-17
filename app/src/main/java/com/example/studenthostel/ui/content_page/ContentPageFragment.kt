package com.example.studenthostel.ui.content_page

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.studenthostel.base.BaseFragment
import com.example.studenthostel.databinding.FragmentContentPageBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ContentPageFragment : BaseFragment<FragmentContentPageBinding,
        ContentPageContract.ContentPageState,
        ContentPageContract.ContentPageEvent,
        ContentPageContract.ContentPageEffect>() {

    override val viewModel by viewModels<ContentPageViewModel>()

    override fun setContent() {
        viewModel.event(ContentPageContract.ContentPageEvent.LoadApartment)

        binding.btnConfirm.setOnClickListener {
            viewModel.state.apartment?.id?.let { id ->
                viewModel.event(ContentPageContract.ContentPageEvent.OnBookNowClicked(apartmentId = id))
            }
        }
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentContentPageBinding = FragmentContentPageBinding.inflate(inflater, container, false)

    override fun onEffect(viewEffect: ContentPageContract.ContentPageEffect) {
        when (viewEffect) {
            ContentPageContract.ContentPageEffect.NavigateBack -> navController.popBackStack()
            is ContentPageContract.ContentPageEffect.NavigateToBooking -> {
                val action =
                    ContentPageFragmentDirections.actionContactPageFragmentToBookingFragment2()
                navController.navigate(action)
            }
        }
    }


    @SuppressLint("SetTextI18n")
    override fun onUiStateChange(viewState: ContentPageContract.ContentPageState) {

        // Migrate to Data Binding
        // Handle date format
        if (viewState.apartment != null) {
            binding.apply {
                priceOfAd.text = "${viewState.apartment.price} EGP"
                addressOfAd.text = viewState.apartment.address
                valueAdvertiseStatus.text = viewState.apartment.apartmentStatusType.toString()
                valueFloorLocation.text = viewState.apartment.floor.toString()
                valuePublishedDate.text = viewState.apartment.date
                valueRoomLivingNumber.text = viewState.apartment.roomCounter.toString()
            }
        }
    }
}