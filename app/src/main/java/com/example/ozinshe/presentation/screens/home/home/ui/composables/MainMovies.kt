package com.example.ozinshe.presentation.screens.home.home.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.ozinshe.data.models.MainMovie
import com.example.ozinshe.presentation.screens.home.home.viewmodel.HomeScreenViewModel
import com.example.ozinshe.presentation.utils.getMovieType

@Composable
fun MainMovies(
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val mainMoviesState by viewModel.mainMovieState.collectAsState()

    LaunchedEffect(mainMoviesState.mainMovies) {
        if(mainMoviesState.mainMovies.isNullOrEmpty()) {
            viewModel.fetchMainMovies()
        }
    }

    LazyRow(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    ) {
        items(mainMoviesState.mainMovies!!) { movie ->
            if (mainMoviesState.isLoading) {

            } else {
                MainMovieItem(movie)
                Spacer(modifier = Modifier.width(16.dp))
            }
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