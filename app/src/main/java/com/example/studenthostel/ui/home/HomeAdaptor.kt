package com.example.studenthostel.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.studenthostel.base.BaseAdapter
import com.example.studenthostel.databinding.ApartmentHolderBinding
import com.example.studenthostel.model.Apartment

class HomeAdaptor: BaseAdapter<Apartment, ApartmentHolderBinding>() {
    override fun getBinding(parent: ViewGroup, viewType: Int): ApartmentHolderBinding =
        ApartmentHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun bindData(binding: ApartmentHolderBinding, item: Apartment, position: Int) {
        binding.apartment = item
    }

}