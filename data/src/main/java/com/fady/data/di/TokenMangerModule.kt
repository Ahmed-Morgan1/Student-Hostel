package com.fady.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.fady.data.dataSoure.local.prefrances.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TokenMangerModule {

    @Provides
    @Singleton
    fun provideTokenManager(
        dataStore: DataStore<Preferences>
    ): TokenManager = TokenManager(dataStore)

}