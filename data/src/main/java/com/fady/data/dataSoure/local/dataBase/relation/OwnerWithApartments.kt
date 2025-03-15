package com.fady.data.dataSoure.local.dataBase.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.fady.data.dto.ApartmentDto
import com.fady.data.dto.OwnerDto

/**
 * This class is used to get the apartments of an owner
 * Relation between the owner and the apartments
 * one to one relationship
 */
data class OwnerWithApartments(
    @Embedded
    val owner: OwnerDto,
    @Relation(
        parentColumn = "id",
        entityColumn = "ownerId",
        entity = ApartmentDto::class
    )
    val apartments: List<ApartmentDto>

) {
    fun toOwner() = OwnerDto(
        ownerPhone = this.owner.ownerPhone,
        id = this.owner.id,
        ownerFName = this.owner.ownerFName,
        ownerLName = this.owner.ownerLName,
        ownerEmail = this.owner.ownerEmail,
//        apartments = this.apartments

    )
}
