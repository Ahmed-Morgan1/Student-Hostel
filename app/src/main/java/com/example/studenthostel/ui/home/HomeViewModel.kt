package com.example.studenthostel.ui.home

import androidx.lifecycle.viewModelScope
import com.example.studenthostel.base.BaseViewModel
import com.example.studenthostel.mapper.toApartment
import com.example.studenthostel.mapper.toApartmentList
import com.example.studenthostel.model.Apartment
import com.fady.data.repo.base.IApartmentRepo
import com.fady.data.repo.base.IFavouriteApartmentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apartmentRepository: IApartmentRepo,
    private val favouriteApartmentRepository: IFavouriteApartmentRepo
) : BaseViewModel<HomeContract.HomeState, HomeContract.HomeEvent, HomeContract.HomeEffect>

    (HomeContract.HomeState()) {

    override fun event(event: HomeContract.HomeEvent) {
        when (event) {
            is HomeContract.HomeEvent.OnApartmentClicked -> sendEffect(HomeContract.HomeEffect.NavigateToDetails(event.apartment))
            HomeContract.HomeEvent.OnClickAllFeaturedSales -> sendEffect(
                HomeContract.HomeEffect.NavigateToAllSales(
                    state.apartments.filter { it.apartmentStatusType == Apartment.ApartmentStatusType.SALE })
            )
            is HomeContract.HomeEvent.OnSearchQueryChanged -> setState(state.copy(searchQuery = event.query))
            HomeContract.HomeEvent.OnClickALlFeaturedRental -> sendEffect(
                HomeContract.HomeEffect.NavigateToAllRental(
                    state.apartments.filter { it.apartmentStatusType == Apartment.ApartmentStatusType.RENT })
            )

            HomeContract.HomeEvent.OnClickAllRental -> getAllApartments(isSalesOnly = false)
            HomeContract.HomeEvent.OnClickAllSales -> getAllApartments(isSalesOnly = true)
            is HomeContract.HomeEvent.OnClickApartment -> getApartmentDetails(event.apartment.id)
            HomeContract.HomeEvent.OnFetchApartments -> getAllApartments()
            is HomeContract.HomeEvent.OnToggleApartmentFromFavourites -> toggleFavApartment(event.apartment)
        }
    }


    private fun toggleFavApartment(apartment: Apartment) {
        viewModelScope.launch(Dispatchers.IO) {
            if (apartment.isFavourite) favouriteApartmentRepository
                .removeApartmentFromFavourite(apartment.id)
            else favouriteApartmentRepository
                .addApartmentToFavourite(apartment.id)
        }
    }

    private fun getAllApartments(isSalesOnly: Boolean? = null) {
        setState(state.copy(isLoading = true))
        viewModelScope.launch(Dispatchers.IO) {
           apartmentRepository.getAllApartments()
                .map {
                    it.toApartmentList()
                }.collect { apartmentList ->
                    if (isSalesOnly == null) setState(state.copy(apartments = apartmentList))
                    else if (isSalesOnly) setState(state.copy(apartments = apartmentList.filter { it.apartmentStatusType == Apartment.ApartmentStatusType.SALE }))
                    else setState(state.copy(apartments = apartmentList.filter { it.apartmentStatusType == Apartment.ApartmentStatusType.RENT }))
                }
        }

    }


    private fun getApartmentDetails(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val apartment = apartmentRepository.getApartmentById(id).toApartment()
            sendEffect(HomeContract.HomeEffect.NavigateToDetails(apartment))
        }
    }
}