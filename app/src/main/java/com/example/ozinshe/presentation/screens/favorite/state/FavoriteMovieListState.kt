package com.example.ozinshe.presentation.screens.favorite.state

import com.example.ozinshe.data.models.Movie

data class FavoriteMovieListState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val favoriteMovies: List<Movie> = emptyList()
)
