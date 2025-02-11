package com.fady.data.di

import com.fady.data.networkConnectivity.ConnectivityHandler
import com.fady.data.networkConnectivity.IConnectivityHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class ConnectivityHandlerBinder {
    @Binds
    abstract fun bindConnectivityHandler(connectivityHandler: ConnectivityHandler): IConnectivityHandler
}