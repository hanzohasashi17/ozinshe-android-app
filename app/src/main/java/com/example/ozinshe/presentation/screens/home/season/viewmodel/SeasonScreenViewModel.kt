package com.example.ozinshe.presentation.screens.home.season.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe.data.models.Season
import com.example.ozinshe.data.repositories.MovieRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeasonScreenViewModel @Inject constructor(
    private val movieRepositoryImpl: MovieRepositoryImpl
): ViewModel() {

    var movieSeasons = mutableStateOf<List<Season>>(emptyList())

    fun fetchMovieSeasons(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val seasons = movieRepositoryImpl.getMovieSeasons(movieId)
            movieSeasons.value = seasons
        }
    }
}