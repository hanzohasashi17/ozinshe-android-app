package com.example.ozinshe.presentation.screens.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe.data.models.Movie
import com.example.ozinshe.data.repositories.MovieRepositoryImpl
import com.example.ozinshe.presentation.screens.search.state.SearchCategoriesState
import com.example.ozinshe.presentation.screens.search.state.SearchResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieRepositoryImpl: MovieRepositoryImpl
): ViewModel() {
    private var _searchMovieState = MutableStateFlow(SearchResultState())
    val searchMovieState = _searchMovieState.asStateFlow()

    private var _searchCategoriesState = MutableStateFlow(SearchCategoriesState())
    val searchCategoriesState = _searchCategoriesState.asStateFlow()

    init {
        fetchCategories()
    }

    fun searchMovie(query: String) {
        val trimmedQuery = query.trim()

        if (trimmedQuery.isEmpty()) {
            return
        }

        _searchMovieState.update { currentState ->
            currentState.copy(isLoading = true, errorMessage = null, noMoviesFound = false)
        }

        viewModelScope.launch {
            try {
                val movies = movieRepositoryImpl.searchMovies(trimmedQuery)
                if (movies.isEmpty()) {
                    _searchMovieState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            searchResults = movies,
                            noMoviesFound = true
                        )
                    }
                }
                _searchMovieState.update { currentState ->
                    currentState.copy(isLoading = false, searchResults = movies)
                }
            } catch (e: Exception) {
                _searchMovieState.update { currentState ->
                    currentState.copy(isLoading = true, errorMessage = e.message)
                }
            }
        }
    }

    private fun fetchCategories() {
        _searchCategoriesState.update { currentState ->
            currentState.copy(isLoading = true, errorMessage = null)
        }
        viewModelScope.launch {
            try {
                val categories = movieRepositoryImpl.getMovieCategories()
                _searchCategoriesState.update { currentState ->
                    currentState.copy(isLoading = false, categories = categories)
                }
            } catch (e: Exception) {
                _searchCategoriesState.update { currentState ->
                    currentState.copy(errorMessage = e.message)
                }
            }
        }
    }
}