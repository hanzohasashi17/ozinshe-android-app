package com.example.ozinshe.presentation.screens.search.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ozinshe.presentation.screens.home.movie_list.ui.SearchCategoriesComponent
import com.example.ozinshe.presentation.screens.search.ui.components.SearchCategories
import com.example.ozinshe.presentation.screens.search.ui.components.SearchHeader
import com.example.ozinshe.presentation.screens.search.ui.components.SearchMoviesListComponent
import com.example.ozinshe.presentation.screens.search.ui.components.SearchResultList
import com.example.ozinshe.presentation.screens.search.ui.components.SearchTextField
import com.example.ozinshe.presentation.screens.search.viewmodel.SearchViewModel
import com.example.ozinshe.presentation.utils.HeaderTitle
import com.example.ozinshe.presentation.utils.HorizontalLine

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val categoriesState by viewModel.searchCategoriesState.collectAsState()
    val moviesState by viewModel.searchMovieState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
    ) {
        SearchHeader()
        HorizontalLine()
        Spacer(modifier = Modifier.height(24.dp))
        SearchTextField { viewModel.searchMovie(it) }
        Spacer(modifier = Modifier.height(24.dp))
        Column {
            when {
                moviesState.isLoading -> {
                    HeaderTitle(text = "Іздеу нәтижелері")
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }

                moviesState.errorMessage != null -> {
                    HeaderTitle(text = "Іздеу нәтижелері")
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = moviesState.errorMessage!!,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.TopCenter),
                            color = MaterialTheme.colorScheme.onError
                        )
                    }
                }

                moviesState.noMoviesFound -> {
                    HeaderTitle(text = "Іздеу нәтижелері")
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Нет таких фильмов :(", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }

                moviesState.searchResults?.isEmpty() == true -> {
                    SearchCategoriesComponent(categoriesState = categoriesState)
                }


                else -> {
                    HeaderTitle(text = "Іздеу нәтижелері")
                    SearchResultList(movies = moviesState.searchResults)
                }
            }
        }
    }
}