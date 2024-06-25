package com.example.ozinshe.presentation.screens.home.home.state

import com.example.ozinshe.data.models.MovieByCategory

data class MovieByCategoryState (
    val movieList: List<MovieByCategory>? = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
