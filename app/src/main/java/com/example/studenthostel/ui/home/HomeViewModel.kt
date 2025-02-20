package com.example.studenthostel.ui.home

import androidx.lifecycle.viewModelScope
import com.example.studenthostel.base.BaseViewModel
import com.example.studenthostel.mapper.toApartment
import com.example.studenthostel.model.Apartment
import com.fady.data.repo.impl.ApartmentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apartmentRepository: ApartmentRepo
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
        }
    }

    private fun getAllApartments(isSalesOnly: Boolean? = null) {
        setState(state.copy(isLoading = true))
        viewModelScope.launch {
            val apartments = apartmentRepository.getAllApartments().map {
                it.toApartment()
            }
            if (isSalesOnly == null) setState(state.copy(apartments = apartments))
            else if (isSalesOnly)
                setState(state.copy(apartments = apartments.filter { it.apartmentStatusType == Apartment.ApartmentStatusType.SALE }))
            else
                setState(state.copy(apartments = apartments.filter { it.apartmentStatusType == Apartment.ApartmentStatusType.RENT }))
        }
    }

    private fun getApartmentDetails(id: Int) {
        viewModelScope.launch {
            val apartment = apartmentRepository.getApartmentById(id).toApartment()
            sendEffect(HomeContract.HomeEffect.NavigateToDetails(apartment))
        }
    }
}