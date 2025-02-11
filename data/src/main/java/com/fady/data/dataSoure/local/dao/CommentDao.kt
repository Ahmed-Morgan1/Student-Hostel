package com.fady.data.dataSoure.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.fady.data.dto.CommentDto

@Dao
interface CommentDao {

    @Insert
    suspend fun insertComment(comment: CommentDto)

    @Delete
    suspend fun deleteComment(comment: CommentDto)

}