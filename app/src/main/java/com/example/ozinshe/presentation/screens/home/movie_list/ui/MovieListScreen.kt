package com.example.ozinshe.presentation.screens.home.movie_list.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.Coil
import coil.compose.AsyncImage
import com.example.ozinshe.data.models.Movie
import com.example.ozinshe.presentation.screens.home.movie_list.MovieListState
import com.example.ozinshe.presentation.screens.home.movie_list.viewmodel.MovieListViewModel
import com.example.ozinshe.presentation.utils.getMovieType

@Composable
fun MovieListScreen(
    categoryId: Int?,
    navToInfoScreen: (movieId: Int?) -> Unit,
    viewModel: MovieListViewModel = hiltViewModel()
) {

    val movieList by viewModel.movieList.collectAsState()

    LaunchedEffect(Unit) {
        if (categoryId != null) {
            viewModel.fetchMovieListByCategoryId(categoryId)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        when (movieList) {
            is MovieListState.Error -> {

            }

            MovieListState.Initial -> {

            }

            MovieListState.Loading -> {

            }

            is MovieListState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp, vertical = 50.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    (movieList as MovieListState.Success).movieList.forEach { movie ->
                        MovieListItem(
                            movie = movie,
                            navToInfoScreen = navToInfoScreen
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun MovieListItem(
    movie: Movie,
    navToInfoScreen: (movieId: Int) -> Unit
) {
    val cachePath = Coil.imageLoader(context = LocalContext.current).diskCache?.directory?.toNioPath()
    Log.d("Coil", "Cache path: $cachePath")
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        AsyncImage(
            model = movie.poster.link,
            contentDescription = null,
            modifier = Modifier
                .height(104.dp)
                .width(71.dp)
        )
        Column {
            Text(
                text = movie.name,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = movie.year.toString(),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Circle",
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.onSurfaceVariant,
                            shape = CircleShape
                        )
                        .size(4.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = getMovieType(movie.movieType),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Button(
                onClick = { navToInfoScreen(movie.id) }
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Қарау"
                )
            }
            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}