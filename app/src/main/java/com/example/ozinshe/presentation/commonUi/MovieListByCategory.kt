package com.example.ozinshe.presentation.commonUi

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ozinshe.data.models.Movie
import com.example.ozinshe.presentation.utils.getMovieType

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieListByCategory(
    movieListByCategory: List<Movie>,
    navToMovieInfoScreen: (movieId: String) -> Unit
) {
    LazyRow {
        items(movieListByCategory) { movie ->
            MovieItemByCategory(
                imageUrl = movie.poster.link,
                title = movie.name,
                movieType = getMovieType(movie.movieType),
                id = movie.id,
                navToMovieInfoScreen = navToMovieInfoScreen
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}

@Composable
fun MovieItemByCategory(
    imageUrl: String,
    title: String,
    movieType: String,
    id: Int,
    navToMovieInfoScreen: (String) -> Unit
) {
    Column {
        Box(
            modifier = Modifier
                .width(112.dp)
                .height(164.dp)
                .clip(RoundedCornerShape(12.dp))
                .clickable { navToMovieInfoScreen(id.toString()) }
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = title,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.width(112.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = movieType,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.width(112.dp)
        )
    }
}