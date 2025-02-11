package com.fady.data.networkConnectivity

import kotlinx.coroutines.flow.Flow

interface IConnectivityHandler {
    fun observeConnectivity(): Flow<NetworkConnectivityStatus>
    fun isNetworkAvailable():NetworkConnectivityStatus
}