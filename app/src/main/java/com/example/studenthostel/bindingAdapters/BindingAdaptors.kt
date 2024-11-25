package com.example.studenthostel.bindingAdapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.studenthostel.R
import com.example.studenthostel.model.Apartment

@BindingAdapter("app:type")
fun type(view: TextView, type: Apartment.ApartmentStatusType) {
    view.text = view.context.getString(R.string.apartment_for_type, type)
}

@BindingAdapter("imgUrl")
fun imgUrl(view: ImageView, url: String)  {
    //Glide code
}