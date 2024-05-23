package com.example.ozinshe.presentation.screens.search.state

import com.example.ozinshe.data.models.Movie

data class SearchResultState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val searchResults: List<Movie>? = emptyList(),
    val noMoviesFound: Boolean = false
)