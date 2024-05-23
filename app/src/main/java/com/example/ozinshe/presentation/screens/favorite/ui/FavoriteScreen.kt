package com.example.ozinshe.presentation.screens.favorite.ui

import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ozinshe.presentation.screens.favorite.ui.components.FavoriteHeader
import com.example.ozinshe.presentation.screens.favorite.ui.components.FavoriteMovieListComponent
import com.example.ozinshe.presentation.screens.favorite.viewmodel.FavoriteViewModel
import com.example.ozinshe.presentation.utils.HorizontalLine

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val favoriteMovieListState by viewModel.favoriteMovieListState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchMovies()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
    ) {
        FavoriteHeader()
        HorizontalLine()
        Spacer(modifier = Modifier.height(24.dp))
        when {
            favoriteMovieListState.isLoading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
            favoriteMovieListState.errorMessage != null -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = favoriteMovieListState.errorMessage!!,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.TopCenter),
                        color = MaterialTheme.colorScheme.onError
                    )
                }
            }
            else -> {
                Log.d("fav", favoriteMovieListState.favoriteMovies.toString())
                FavoriteMovieListComponent(movies = favoriteMovieListState.favoriteMovies)
            }
        }
    }
}