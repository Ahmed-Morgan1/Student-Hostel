package com.fady.data.dataSoure.local.dataBase.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.fady.data.dto.CommentDto

@Dao
interface CommentDao {

    @Insert
    suspend fun insertComment(comment: CommentDto)

    @Delete
    suspend fun deleteComment(comment: CommentDto)

}