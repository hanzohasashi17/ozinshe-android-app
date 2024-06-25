package com.example.ozinshe.presentation.screens.home.movie_list

import com.example.ozinshe.data.models.Movie
import com.example.ozinshe.data.models.MoviesByCategoryResult
import kotlinx.coroutines.flow.MutableStateFlow

data class MovieListState(
    val movies: List<Movie>? = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)