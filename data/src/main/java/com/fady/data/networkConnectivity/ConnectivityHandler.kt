package com.fady.data.networkConnectivity

import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ConnectivityHandler @Inject constructor(
    private val connectivityManager: ConnectivityManager
) : IConnectivityHandler {

    override fun isNetworkAvailable(): NetworkConnectivityStatus =
        if (connectivityManager.activeNetwork != null)
            NetworkConnectivityStatus.CONNECTED
        else NetworkConnectivityStatus.NOT_CONNECTED


    override fun observeConnectivity(): Flow<NetworkConnectivityStatus> = callbackFlow {

        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                launch {
                    send(NetworkConnectivityStatus.CONNECTED)
                }
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                launch {
                    send(NetworkConnectivityStatus.NOT_CONNECTED)
                }
            }

            override fun onUnavailable() {
                super.onUnavailable()
                launch {
                    send(NetworkConnectivityStatus.NOT_CONNECTED)
                }
            }

            override fun onLosing(network: Network, maxMsToLive: Int) {
                super.onLosing(network, maxMsToLive)
                launch {
                    send(NetworkConnectivityStatus.NOT_CONNECTED)
                }
            }
        }

        connectivityManager.registerDefaultNetworkCallback(networkCallback)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    }

}
