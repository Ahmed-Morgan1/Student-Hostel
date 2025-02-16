package com.fady.data.di

import android.content.Context
import androidx.room.Room
import com.fady.data.dataSoure.local.RoomHostel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ): RoomHostel = Room.databaseBuilder(context, RoomHostel::class.java, "RoomHostel").build()


    @Provides
    @Singleton
    fun provideApartmentDao(roomHostel: RoomHostel) = roomHostel.apartmentDao

    @Provides
    @Singleton
    fun provideCommentDao(roomHostel: RoomHostel) = roomHostel.commentDao

    @Provides
    @Singleton
    fun provideOwnerDao(roomHostel: RoomHostel) = roomHostel.ownerDao

    @Provides
    @Singleton
    fun provideStudentDao(roomHostel: RoomHostel) = roomHostel.studentDao

}