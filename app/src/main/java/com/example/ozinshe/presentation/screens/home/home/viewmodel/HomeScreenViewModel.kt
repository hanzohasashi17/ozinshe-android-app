package com.example.ozinshe.presentation.screens.home.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe.data.models.MainMovie
import com.example.ozinshe.data.models.MovieByCategory
import com.example.ozinshe.data.models.ServerResponse
import com.example.ozinshe.data.repositories.MovieRepositoryImpl
import com.example.ozinshe.presentation.screens.home.home.state.MainMoviesState
import com.example.ozinshe.presentation.screens.home.home.state.MovieByCategoryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val movieRepository: MovieRepositoryImpl,
) : ViewModel() {

    private val _mainMovieUiState = MutableStateFlow<MainMoviesState>(MainMoviesState.Loading)
    val mainMovieUiState: StateFlow<MainMoviesState> = _mainMovieUiState.asStateFlow()

    private val _movieByCategoryUiState = MutableStateFlow<MovieByCategoryState>(MovieByCategoryState.Loading)
    val movieByCategoryUiState: StateFlow<MovieByCategoryState> = _movieByCategoryUiState.asStateFlow()

    fun fetchMainMovies() {
        viewModelScope.launch {
            try {
                val mainMovies = movieRepository.getMainMovies()
                _mainMovieUiState.value = MainMoviesState.Success(mainMovies)
            } catch (e: Exception) {
                _mainMovieUiState.value = MainMoviesState.Error("Failed to main movies: ${e.message}")
            }
        }
    }

    fun fetchMoviesByCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val moviesByCategory: List<MovieByCategory> =
                    movieRepository.getMoviesByCategory()
                _movieByCategoryUiState.value = MovieByCategoryState.Success(moviesByCategory)
            } catch (e: Exception) {
                _movieByCategoryUiState.value = MovieByCategoryState.Error("Failed to movies category list:${e.message}")
            }
        }
    }
}