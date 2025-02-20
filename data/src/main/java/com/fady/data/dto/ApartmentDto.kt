package com.fady.data.dto

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "apartment",
    foreignKeys = [
        ForeignKey(
            entity = OwnerDto::class,
            parentColumns = ["ownerId"],
            childColumns = ["ownerId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class ApartmentDto(

    @field:SerializedName("address")
    val address: String? = null,

    @field:SerializedName("owner_Id")
    val ownerId: Int? = null,

    @field:SerializedName("num_Room")
    val numRoom: Int,

    @field:SerializedName("num_Bed")
    val numBed: Int? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("publisheddate")
    val publishedDate: String? = null,

    @field:SerializedName("location_Image")
    val locationImage: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("floorNum")
    val floorNum: Int,

    @PrimaryKey(autoGenerate = true)
    @field:SerializedName("apartment_Id")
    val apartmentId: Int = 0,

    @field:SerializedName("price")
    val price: Float,

    @field:SerializedName("isRented")
    val isRented: Boolean? = null

){
    @Ignore
    @field:SerializedName("comments")
    val comments: List<CommentDto>? = null

    @Ignore
    @field:SerializedName("owner")
    val owner: OwnerDto? = null
}
