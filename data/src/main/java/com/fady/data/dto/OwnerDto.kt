package com.fady.data.dto

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("Owner")
data class OwnerDto(

    @field:SerializedName("owner_Phone")
    val ownerPhone: Int? = null,

    @PrimaryKey(autoGenerate = true)
    @field:SerializedName("owner_Id")
    val ownerId: Int = 0,

    @field:SerializedName("owner_FName")
    val ownerFName: String? = null,

    @field:SerializedName("owner_LName")
    val ownerLName: String? = null,

    @field:SerializedName("owner_Email")
    val ownerEmail: String? = null,

){
    @Ignore
    @field:SerializedName("apartments")
    val apartments: List<ApartmentDto>? = null
}
