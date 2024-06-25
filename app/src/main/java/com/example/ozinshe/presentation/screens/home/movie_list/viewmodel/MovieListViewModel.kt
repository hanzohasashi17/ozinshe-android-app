package com.example.ozinshe.presentation.screens.home.movie_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe.data.repositories.MovieRepositoryImpl
import com.example.ozinshe.presentation.screens.home.movie_list.MovieListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val moviesRepositoryImpl: MovieRepositoryImpl
) : ViewModel() {

    private var _movieListState = MutableStateFlow(MovieListState())
    val movieListState = _movieListState.asStateFlow()

    fun fetchMovieListByCategoryId(categoryId: Int) {
        _movieListState.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            try {
                val movies = moviesRepositoryImpl.getMoviesByCategoryId(categoryId)
                _movieListState.update { it.copy(isLoading = false, movies = movies.content) }
            } catch (e: Exception) {
                _movieListState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }
}