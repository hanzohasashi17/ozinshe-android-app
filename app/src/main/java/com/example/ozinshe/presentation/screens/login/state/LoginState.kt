package com.example.ozinshe.presentation.screens.login.state

import com.example.ozinshe.data.models.User

data class LoginState(
    val user: User? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
