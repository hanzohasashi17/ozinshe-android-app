package com.example.ozinshe.presentation.screens.auth.state

data class UserTokenState(
    val isLogged: Boolean = false,
    val errorMessage: String? = null
)
