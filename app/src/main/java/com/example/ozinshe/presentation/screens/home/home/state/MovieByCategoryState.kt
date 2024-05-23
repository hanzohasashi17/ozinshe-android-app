package com.example.ozinshe.presentation.screens.home.home.state

import com.example.ozinshe.data.models.MovieByCategory

sealed class MovieByCategoryState {
    object Initial : MovieByCategoryState()
    object Loading : MovieByCategoryState()
    data class Success(val moviesByCategory: List<MovieByCategory>) : MovieByCategoryState()
    data class Error(val error: String) : MovieByCategoryState()
}
