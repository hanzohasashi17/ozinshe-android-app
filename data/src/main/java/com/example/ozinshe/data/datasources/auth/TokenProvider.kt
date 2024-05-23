package com.example.ozinshe.data.datasources.auth

import com.example.ozinshe.data.datasources.localStorage.SharedPreferencesManager
import javax.inject.Inject

class TokenProvider @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager
) {
    fun getToken(): String? {
        return sharedPreferencesManager.getToken()
    }
}