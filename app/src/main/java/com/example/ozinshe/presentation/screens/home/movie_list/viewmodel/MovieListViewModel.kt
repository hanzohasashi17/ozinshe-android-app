package com.example.ozinshe.presentation.screens.home.movie_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe.data.repositories.MovieRepositoryImpl
import com.example.ozinshe.presentation.screens.home.movie_list.MovieListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val moviesRepositoryImpl: MovieRepositoryImpl
) : ViewModel() {
    private var _movieList = MutableStateFlow<MovieListState>(MovieListState.Initial)
    val movieList = _movieList.asStateFlow()

    fun fetchMovieListByCategoryId(categoryId: Int) {
        _movieList.value = MovieListState.Loading
        viewModelScope.launch {
            val data = moviesRepositoryImpl.getMoviesByCategoryId(categoryId)
            _movieList.value = MovieListState.Success(data.content)
            }
        }

}