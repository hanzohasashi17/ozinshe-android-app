package com.example.ozinshe.presentation.screens.home.movie_list

import com.example.ozinshe.data.models.Movie
import com.example.ozinshe.data.models.MoviesByCategoryResult
import kotlinx.coroutines.flow.MutableStateFlow

sealed class MovieListState {
    data object Initial : MovieListState()
    data object Loading : MovieListState()
    data class Success(val movieList: List<Movie>) : MovieListState()
    data class Error(val exception: Exception) : MovieListState()
}