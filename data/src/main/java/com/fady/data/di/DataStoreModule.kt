package com.fady.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "favourite_apartments")

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @FavouriteApartmentDataStore
    @Singleton
    fun provideFavouriteApartmentDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = context.dataStore


}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FavouriteApartmentDataStore