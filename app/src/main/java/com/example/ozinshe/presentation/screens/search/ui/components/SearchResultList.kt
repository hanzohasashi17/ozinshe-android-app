package com.example.ozinshe.presentation.screens.search.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ozinshe.data.models.Movie
import com.example.ozinshe.presentation.screens.home.movie_list.ui.MovieListItem

@Composable
fun SearchResultList(movies: List<Movie>?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        LazyColumn {
            if (movies != null) {
                items(movies) { movie ->
                    MovieListItem(movie = movie, navToInfoScreen = { })
                }
            }
        }
    }
}