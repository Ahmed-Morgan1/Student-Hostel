package com.fady.data.di

import com.fady.data.dataSoure.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object WebServiceModule {
    @Provides
    @Singleton
    fun provideWebServices(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}