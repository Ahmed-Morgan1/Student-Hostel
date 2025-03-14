package com.example.studenthostel.ui.favourate

import androidx.lifecycle.viewModelScope
import com.example.studenthostel.base.BaseViewModel
import com.example.studenthostel.mapper.toApartmentList
import com.example.studenthostel.model.Apartment
import com.fady.data.repo.base.IFavouriteApartmentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val favRepo: IFavouriteApartmentRepo
) : BaseViewModel<FavouriteContract.FavouriteState, FavouriteContract.FavouriteEvent, FavouriteContract.FavouriteEffect>(
    FavouriteContract.FavouriteState()
) {
    override fun event(event: FavouriteContract.FavouriteEvent) {
        when (event) {
            is FavouriteContract.FavouriteEvent.OnApartmentClicked -> sendEffect(
                FavouriteContract.FavouriteEffect.NavigateToDetails(event.apartment)
            )

            is FavouriteContract.FavouriteEvent.OnRemoveApartmentFromFavourites -> removeItem(event.apartment)
            FavouriteContract.FavouriteEvent.OnFetchApartments -> getFavouriteList()
        }
    }

    private fun getFavouriteList() {
        setState(state.copy(isLoading = true))
        viewModelScope.launch {
            favRepo.getAllFavouriteApartments()
                .map {
                    it.toApartmentList()
                }.collectLatest {
                    setState(state.copy(apartments = it))
                }
        }
    }

    private fun removeItem(apartment: Apartment) {
        viewModelScope.launch {
            favRepo.removeApartmentFromFavourite(apartment.id)
//            setState(state.copy(apartments = state.apartments.filter { it.id != apartment.id }))
        }
    }
}