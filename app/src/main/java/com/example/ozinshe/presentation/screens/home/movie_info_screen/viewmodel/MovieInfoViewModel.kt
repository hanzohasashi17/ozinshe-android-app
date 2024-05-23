package com.example.ozinshe.presentation.screens.home.movie_info_screen.viewmodel

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ozinshe.data.models.FavoriteMovieDTO
import com.example.ozinshe.data.repositories.MovieRepositoryImpl
import com.example.ozinshe.presentation.screens.home.movie_info_screen.state.MovieInfoScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieInfoViewModel @Inject constructor(
    private val movieRepositoryImpl: MovieRepositoryImpl
): ViewModel() {

    private val _state = MutableStateFlow<MovieInfoScreenState>(MovieInfoScreenState.Initial)
    val state: StateFlow<MovieInfoScreenState> = _state.asStateFlow()

    fun fetchMovieById(id: Int) {
        _state.value = MovieInfoScreenState.Loading
        viewModelScope.launch {
            try {
                _state.value = MovieInfoScreenState.Success(movieRepositoryImpl.getMovieById(id))
            } catch (e: Exception) {
                Log.d("error", e.message.toString())
            }
        }
    }

    fun addFavoriteMovie(movieId: Int) {
        viewModelScope.launch {
            try {
                movieRepositoryImpl.addFavoriteMovie(FavoriteMovieDTO(movieId))
            } catch (e: Exception) {
                Log.d("error", e.message.toString())
            }
        }
    }
}