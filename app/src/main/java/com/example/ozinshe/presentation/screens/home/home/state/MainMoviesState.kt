package com.example.ozinshe.presentation.screens.home.home.state

import com.example.ozinshe.data.models.MainMovie

data class MainMoviesState(
    val mainMovies: List<MainMovie>? = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
