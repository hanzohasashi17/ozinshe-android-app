package com.example.ozinshe.presentation.screens.favorite.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ozinshe.data.models.Movie
import com.example.ozinshe.presentation.screens.home.movie_list.ui.MovieListItem

@Composable
fun FavoriteMovieListComponent(movies: List<Movie>?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        LazyColumn {
            if (movies != null) {
                items(movies, key = {item: Movie -> item.id}) { movie ->
                    MovieListItem(movie = movie, navToInfoScreen = { })
                }
            }
        }
    }
}