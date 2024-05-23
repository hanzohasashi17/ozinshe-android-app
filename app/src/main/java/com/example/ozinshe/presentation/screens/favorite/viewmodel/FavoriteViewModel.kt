package com.example.ozinshe.presentation.screens.favorite.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ozinshe.data.datasources.localStorage.SharedPreferencesManager
import com.example.ozinshe.data.repositories.MovieRepositoryImpl
import com.example.ozinshe.presentation.screens.auth.viewmodel.AuthViewModel
import com.example.ozinshe.presentation.screens.favorite.state.FavoriteMovieListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val movieRepositoryImpl: MovieRepositoryImpl,
    private val sharedPreferencesManager: SharedPreferencesManager
): ViewModel() {
    private val _favoriteMovieListState = MutableStateFlow(FavoriteMovieListState())
    val favoriteMovieListState = _favoriteMovieListState.asStateFlow()

    private val _userId = MutableStateFlow(sharedPreferencesManager.getUserId())


    fun fetchMovies() {
        _favoriteMovieListState.update { currentState ->
            currentState.copy(isLoading = true, errorMessage = null)
        }
        viewModelScope.launch {
            try {
                val favoriteMovies = movieRepositoryImpl.getFavoriteMovies(_userId.value)
                _favoriteMovieListState.update { currentState ->
                    currentState.copy(isLoading = false, favoriteMovies = favoriteMovies)
                }
            } catch (e: Exception) {
                _favoriteMovieListState.update { currentState ->
                    currentState.copy(isLoading = false, errorMessage = e.message)
                }
            }
        }
    }
}