package com.example.studenthostel.model

data class Apartment(
    val floor: Byte,
    val roomCounter: Int,
    val area: Short,
    val price: Float,
    val address: String,
    val imgCoverUrl: String,
    val apartmentStatusType: ApartmentStatusType=ApartmentStatusType.SALE
) {
    // Enum class to represent the type of apartment (rent or sale)
    enum class ApartmentStatusType {
        RENT {
            override fun toString(): String = "rent"
        },
        SALE {
            override fun toString(): String = "sale"
        }
    }
}
