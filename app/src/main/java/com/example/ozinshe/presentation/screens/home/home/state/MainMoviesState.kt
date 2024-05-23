package com.example.ozinshe.presentation.screens.home.home.state

import com.example.ozinshe.data.models.MainMovie

sealed class MainMoviesState {
    object Initial : MainMoviesState()
    object Loading : MainMoviesState()
    data class Success(val mainMovies: List<MainMovie>) : MainMoviesState()
    data class Error(val error: String) : MainMoviesState()
}
