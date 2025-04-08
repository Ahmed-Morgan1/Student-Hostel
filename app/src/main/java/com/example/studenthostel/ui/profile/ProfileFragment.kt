package com.example.studenthostel.ui.profile

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.studenthostel.base.BaseFragment
import com.example.studenthostel.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<
        FragmentProfileBinding,
        ProfileContract.ProfileState,
        ProfileContract.ProfileEvent,
        ProfileContract.ProfileEffect
        >() {
    override val viewModel by viewModels<ProfileViewModel>()

    override fun setContent() {
        binding.apply {
            Log.e("Tag", "setContent")

            btnSave.setOnClickListener {
                Log.e("Tag", "btnSave")
                viewModel.event(ProfileContract.ProfileEvent.OnSaveProfileClick)
            }
        }
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProfileBinding =
        FragmentProfileBinding.inflate(inflater, container, false)

    override fun onEffect(viewEffect: ProfileContract.ProfileEffect) {
        when (viewEffect) {
            ProfileContract.ProfileEffect.NavigateToBack -> navController.popBackStack()
        }
    }

    override fun onUiStateChange(viewState: ProfileContract.ProfileState) = when (viewState) {
        ProfileContract.ProfileState.Loading -> onLoading(true)
        ProfileContract.ProfileState.ProfileFetched -> onLoading(false)
    }

    private fun onLoading(isLoading: Boolean) {
//        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun onError(error: String?) {

    }


}