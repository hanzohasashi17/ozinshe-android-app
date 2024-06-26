package com.example.ozinshe.presentation.screens.registration.state

import com.example.ozinshe.data.models.User

data class RegistrationState(
    val user: User? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
