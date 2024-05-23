package com.example.ozinshe.data.datasources.localStorage

import android.content.Context
import android.content.SharedPreferences
import com.example.ozinshe.data.models.User

class SharedPreferencesManager(private val context: Context) {
    private val sharedPref: SharedPreferences by lazy {
        context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    }


    // Token
    fun saveToken(token: String) {
        with(sharedPref.edit()) {
            if (token.isEmpty()) {
                putString("token", "")
                apply()
            } else {
                putString("token", "Bearer $token")
                apply()
            }
        }
    }

    fun getToken(): String? {
        return sharedPref.getString("token", null)
    }

    fun deleteToken() {
        with(sharedPref.edit()) {
            remove("token")
            apply()
        }
    }


    // Onboarding seen
    fun saveViewedOnboarding() {
        with(sharedPref.edit()) {
            putBoolean("hasViewedOnboarding", true)
            apply()
        }
    }

    fun getViewedOnboarding(): Boolean {
        return sharedPref.getBoolean("hasViewedOnboarding", false)
    }


    // User Id
    fun saveUserId(userId: Int) {
        with(sharedPref.edit()) {
            putInt("userId", userId)
            apply()
        }
    }

    fun getUserId(): Int {
        return sharedPref.getInt("userId", 0)
    }
}
