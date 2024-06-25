package com.example.ozinshe.presentation.screens.home.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe.data.repositories.MovieRepositoryImpl
import com.example.ozinshe.presentation.screens.home.home.state.MainMoviesState
import com.example.ozinshe.presentation.screens.home.home.state.MovieByCategoryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val movieRepository: MovieRepositoryImpl,
) : ViewModel() {

    private val _mainMovieState = MutableStateFlow(MainMoviesState())
    val mainMovieState = _mainMovieState.asStateFlow()

    private val _moviesByCategoryState = MutableStateFlow(MovieByCategoryState())
    val moviesByCategoryState = _moviesByCategoryState.asStateFlow()

    fun fetchMainMovies() {
        _mainMovieState.update { it.copy(isLoading = false, error = null) }
        viewModelScope.launch {
            try {
                val mainMovies = movieRepository.getMainMovies()
                _mainMovieState.update { it.copy(isLoading = false, mainMovies = mainMovies) }
            } catch (e: Exception) {
                _mainMovieState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    fun fetchMoviesByCategory() {
        _moviesByCategoryState.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            try {
                val moviesByCategory = movieRepository.getMoviesByCategory()
                _moviesByCategoryState.update { it.copy(isLoading = false, movieList = moviesByCategory) }
            } catch (e: Exception) {
                _moviesByCategoryState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }
}