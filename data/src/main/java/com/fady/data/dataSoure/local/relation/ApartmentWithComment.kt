package com.fady.data.dataSoure.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.fady.data.dto.ApartmentDto
import com.fady.data.dto.CommentDto

data class ApartmentWithComment(
    @Embedded
    val apartment: ApartmentDto,
    @Relation(
        parentColumn = "apartmentId",
        entityColumn = "apartmentId",
        entity = CommentDto::class
    )
    val comments: List<CommentDto>
) {
    fun toApartment() = ApartmentDto(
        address = this.apartment.address,
        ownerId = this.apartment.ownerId,
        numRoom = this.apartment.numRoom,
        numBed = this.apartment.numBed,
        description = this.apartment.description,
        publishedDate = this.apartment.publishedDate,
        locationImage = this.apartment.locationImage,
        title = this.apartment.title,
        floorNum = this.apartment.floorNum,
        apartmentId = this.apartment.apartmentId,
        price = this.apartment.price,
        isRented = this.apartment.isRented,
//        comments = this.comments
    )
}
