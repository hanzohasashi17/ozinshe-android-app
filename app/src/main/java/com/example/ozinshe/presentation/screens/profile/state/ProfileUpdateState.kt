package com.example.ozinshe.presentation.screens.profile.state

import android.service.autofill.UserData
import com.example.ozinshe.data.models.UserProfile

data class ProfileUpdateState (
    val userData: UserData? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)