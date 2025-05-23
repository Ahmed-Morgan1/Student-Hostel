package com.example.studenthostel.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.studenthostel.base.BaseAdapter
import com.example.studenthostel.databinding.ApartmentHolderBinding
import com.example.studenthostel.model.Apartment

class HomeAdaptor: BaseAdapter<Apartment, ApartmentHolderBinding>() {
    var onItemClickListener: OnItemClickListener? = null
    var onAddToFavouritesClickListener: OnItemClickListener? = null

    override fun getBinding(parent: ViewGroup, viewType: Int): ApartmentHolderBinding =
        ApartmentHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun bindData(binding: ApartmentHolderBinding, item: Apartment, position: Int) {
        binding.apartment = item
        binding.root.setOnClickListener {
            onItemClickListener?.onItemClick(item)
        }
        binding.imgFav.setOnClickListener {
            onAddToFavouritesClickListener?.onItemClick(item)
        }
    }


    fun interface OnItemClickListener {
        fun onItemClick(item: Apartment)
    }

}