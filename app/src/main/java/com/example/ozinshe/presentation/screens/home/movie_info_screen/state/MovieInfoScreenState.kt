package com.example.ozinshe.presentation.screens.home.movie_info_screen.state

import com.example.ozinshe.data.models.Movie

sealed class MovieInfoScreenState {
    object Initial : MovieInfoScreenState()
    object Loading : MovieInfoScreenState()
    data class Success(val movie: Movie) : MovieInfoScreenState()
    data class Error(val error: Throwable) : MovieInfoScreenState()
}