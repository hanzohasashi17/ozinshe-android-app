package com.example.ozinshe.presentation.screens.search.state

import com.example.ozinshe.data.models.Movie

data class SearchCategoriesState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val categories: List<Movie.Category>? = emptyList()
)
