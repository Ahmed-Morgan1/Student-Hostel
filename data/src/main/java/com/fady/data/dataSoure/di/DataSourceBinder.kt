package com.fady.data.dataSoure.di

import android.content.Context
import androidx.room.Room
import com.fady.data.dataSoure.local.RoomHostel
import com.fady.data.dataSoure.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object DataSourceBinder {

    @Provides
    @Singleton
    fun provideWebServices(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


    @Provides
    @Singleton
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, RoomHostel::class.java, "RoomHostel").build()

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
