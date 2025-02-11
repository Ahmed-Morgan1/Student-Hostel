package com.fady.data.dto

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    "Student",
    foreignKeys = [
        ForeignKey(
            entity = ApartmentDto::class,
            parentColumns = ["apartmentId"],
            childColumns = ["apartmentId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class StudentDto(

    @field:SerializedName("college")
    val college: String? = null,

    @field:SerializedName("apartment_Id")
    var apartmentId: Int? = null,

    @field:SerializedName("student_Phone")
    val studentPhone: Int? = null,

    @field:SerializedName("student_LName")
    val studentLName: String? = null,

    @PrimaryKey(autoGenerate = true)
    @field:SerializedName("student_Id")
    val studentId: Int? = null,

    @field:SerializedName("student_email")
    val studentEmail: String? = null,

    @field:SerializedName("student_FName")
    val studentFName: String? = null
){
    @Ignore
    @field:SerializedName("apartment")
    val apartment: ApartmentDto? = null
}
