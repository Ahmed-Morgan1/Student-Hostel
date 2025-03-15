package com.fady.data.dataSoure.local.prefrances

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class TokenManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("token")
    }

    suspend fun saveToken(token: String){
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }
    suspend fun getToken(): String?{
        val preferences = dataStore.data.first()
        return preferences[TOKEN_KEY]
    }
    suspend fun clearToken(){
        dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }
}