package com.example.ozinshe.presentation.screens.registration.state

import com.example.ozinshe.data.models.User

data class RegistrationState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val user: User? = null
)
