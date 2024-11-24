package com.example.studenthostel.bindingAdapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.studenthostel.model.Apartment

@BindingAdapter("app:type")
fun type(view: TextView, type: Apartment.ApartmentStatusType) {
    view.text = "Apartment for ${type}"
}

@BindingAdapter("imgUrl")
fun imgUrl(view: ImageView, url: String)  {
    //Glide code
}