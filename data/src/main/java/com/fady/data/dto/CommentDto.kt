package com.fady.data.dto

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "comment",
//    foreignKeys = [
//        ForeignKey(
//            entity = StudentDto::class,
//            parentColumns = ["studentId"],
//            childColumns = ["studentId"],
//            onDelete = ForeignKey.CASCADE,
//            onUpdate = ForeignKey.CASCADE
//        ),
//        ForeignKey(
//            entity = ApartmentDto::class,
//            parentColumns = ["apartmentId"],
//            childColumns = ["apartmentId"],
//            onDelete = ForeignKey.CASCADE,
//            onUpdate = ForeignKey.CASCADE
//        )
//    ]
)
data class CommentDto(
    @PrimaryKey
    val commentId: Int,
    val commentText: String,
    val studentId: Int,
    val apartmentId: Int,
    val commentDate: String
)
