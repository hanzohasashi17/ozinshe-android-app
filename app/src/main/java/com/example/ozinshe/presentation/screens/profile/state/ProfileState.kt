package com.example.ozinshe.presentation.screens.profile.state

import com.example.ozinshe.data.models.UserProfile

data class ProfileState (
    val userData: UserProfile? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)