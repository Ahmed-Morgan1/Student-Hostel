package com.example.studenthostel.mapper

import com.example.studenthostel.model.Apartment
import com.fady.data.dto.ApartmentDto

fun ApartmentDto.toApartment(): Apartment = Apartment(
    id = this.id,
    floor = this.floorNum,
    roomCounter = this.numRoom,
    area = 0,
    price = this.price,
    address = this.address,
    imgCoverUrl = this.locationImage,
    date = this.publishedDate,
    apartmentStatusType = if (this.isRented == true) Apartment.ApartmentStatusType.RENT else Apartment.ApartmentStatusType.SALE
)