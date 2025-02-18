package com.fady.data.dataSoure.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.fady.data.dto.ApartmentDto
import com.fady.data.dto.StudentDto

data class StudentWithApartment(
    @Embedded
    val student: StudentDto,
    @Relation(
        parentColumn = "apartmentId",
        entityColumn = "id",
        entity = ApartmentDto::class
    )
    val apartment: ApartmentDto?
){
    fun toStudent() = StudentDto(
        college = this.student.college,
        apartmentId = this.student.apartmentId,
        studentPhone = this.student.studentPhone,
        studentLName = this.student.studentLName,
        studentId = this.student.studentId,
        studentEmail = this.student.studentEmail,
        studentFName = this.student.studentFName,
//        apartment = this.apartment
    )
}
