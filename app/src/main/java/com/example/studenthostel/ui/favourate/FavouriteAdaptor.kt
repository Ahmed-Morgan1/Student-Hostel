package com.example.studenthostel.ui.favourate

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.studenthostel.base.BaseAdapter
import com.example.studenthostel.databinding.FavApartmentHolderBinding
import com.example.studenthostel.model.Apartment

class FavouriteAdaptor : BaseAdapter<Apartment, FavApartmentHolderBinding>() {

    var onItemClickListener: OnItemClickListener? = null
    var onAddToFavouritesClickListener: OnItemClickListener? = null

    override fun getBinding(parent: ViewGroup, viewType: Int): FavApartmentHolderBinding =
        FavApartmentHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun bindData(binding: FavApartmentHolderBinding, item: Apartment, position: Int) {
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