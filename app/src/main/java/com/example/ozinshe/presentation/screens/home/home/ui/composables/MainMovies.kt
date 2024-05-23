package com.example.ozinshe.presentation.screens.home.home.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ozinshe.data.models.MainMovie
import com.example.ozinshe.presentation.screens.home.home.state.MainMoviesState
import com.example.ozinshe.presentation.utils.getMovieType
import com.example.ozinshe.presentation.utils.shimmerLoader

@Composable
fun MainMovies(
    mainMoviesState: MainMoviesState,
) {
    when (mainMoviesState) {
        is MainMoviesState.Success -> {
            MainMovieList(movies = mainMoviesState.mainMovies)
        }

        is MainMoviesState.Error -> {
            Text(text = mainMoviesState.error.toString())
        }

        MainMoviesState.Initial -> {
            Text(text = "INITIAL")
        }

        MainMoviesState.Loading -> {
            Row(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            ) {
                repeat(3) { movie ->
                    Column {
                        Box(
                            modifier = Modifier
                                .height(160.dp)
                                .width(300.dp)
                                .clip(RoundedCornerShape(12.dp))
                        ) {
                            Spacer(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .shimmerLoader()
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 16.dp))
                        Spacer(
                            modifier = Modifier
                                .height(20.dp)
                                .width(100.dp)
                                .shimmerLoader()
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        }
    }
}


@Composable
fun MainMovieList(movies: List<MainMovie>) {
    LazyRow(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    ) {
        items(movies) { movie ->
            MainMovieItem(movie)
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}


@Composable
fun MainMovieItem(movie: MainMovie) {
    Column {
        Box(
            modifier = Modifier
                .height(160.dp)
                .width(300.dp)
                .clip(RoundedCornerShape(12.dp))
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = movie.link,
                contentDescription = "Main movie",
            )
            Card(
                modifier = Modifier.padding(8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    Color.Transparent,
                    Color.Transparent
                )
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    text = getMovieType(movie.movie.movieType),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Text(
            text = movie.movie.name,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}