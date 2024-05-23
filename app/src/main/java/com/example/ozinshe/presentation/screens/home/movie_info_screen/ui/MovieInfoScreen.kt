package com.example.ozinshe.presentation.screens.home.movie_info_screen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.ozinshe.data.models.FavoriteMovieDTO
import com.example.ozinshe.data.models.Movie
import com.example.ozinshe.presentation.screens.home.movie_info_screen.state.MovieInfoScreenState
import com.example.ozinshe.presentation.screens.home.movie_info_screen.viewmodel.MovieInfoViewModel
import com.example.ozinshe.presentation.utils.HorizontalLine
import com.example.ozinshe.presentation.utils.getMovieType
import com.example.ozinshe.presentation.utils.toPortraitOrientation


@Composable
fun MovieInfoScreen(
    movieId: String?,
    navToYTPlayer: (movieLink: String) -> Unit,
    navToSeasonsScreen: (movieId: Int) -> Unit,
    viewModel: MovieInfoViewModel = hiltViewModel(),
) {
    toPortraitOrientation()

    val movieState by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        if (movieId != null) {
            viewModel.fetchMovieById(movieId.toInt())
        }
    }

    when (movieState) {
        is MovieInfoScreenState.Error -> {

        }

        MovieInfoScreenState.Initial -> {

        }

        MovieInfoScreenState.Loading -> {

        }

        is MovieInfoScreenState.Success -> {
            MovieInfoScreenInner(
                movie = (movieState as MovieInfoScreenState.Success).movie,
                navToYTPlayer = navToYTPlayer,
                navToSeasonsScreen = navToSeasonsScreen,
                saveToFavorites = { viewModel.addFavoriteMovie(it) }
            )
        }
    }
}

@Composable
fun MovieInfoScreenInner(
    movie: Movie,
    navToYTPlayer: (movieLink: String) -> Unit,
    navToSeasonsScreen: (movieId: Int) -> Unit,
    saveToFavorites: (movieId: Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        MovieImage(
            movieImage = movie.poster,
            movieLink = movie.video?.link,
            navToYTPLayer = navToYTPlayer,
            navToSeasonsScreen = navToSeasonsScreen,
            movieId = movie.id,
            saveToFavorites = saveToFavorites
        )
        Column(Modifier.offset(0.dp, (-20).dp)) {
            Column(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(24.dp)
                    )
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                Text(
                    text = movie.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = movie.year.toString(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(Modifier.width(4.dp))
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
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = getMovieType(movie.movieType),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(Modifier.width(4.dp))
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
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = movie.seasonCount.toString(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = movie.seriesCount.toString(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = movie.timing.toString(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                HorizontalLine()
                Spacer(modifier = Modifier.height(24.dp))
                MovieDescriptionText(text = movie.description)
                Spacer(modifier = Modifier.height(24.dp))
                HorizontalLine()
                Spacer(modifier = Modifier.height(24.dp))
                MovieAuthorsInfo(movie.director, movie.producer)
                Spacer(modifier = Modifier.height(24.dp))
                MovieSeasonInfo(movie.seasonCount.toString(), movie.seriesCount.toString())
                Spacer(modifier = Modifier.height(24.dp))
                MovieScreenshotsList(movie.screenshots)
            }
        }

    }

}

@Composable
fun MovieImage(
    movieImage: Movie.Poster,
    movieLink: String?,
    navToYTPLayer: (movieLink: String) -> Unit,
    navToSeasonsScreen: (movieId: Int) -> Unit,
    movieId: Int,
    saveToFavorites: (movieId: Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            model = movieImage.link,
            contentDescription = "Movie image",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 60.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(
                onClick = { saveToFavorites(movieId) }
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Save to favorites",
                    tint = Color.White,
                    modifier = Modifier
                        .size(30.dp)
                )
            }
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary, CircleShape)
                    .size(64.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play movie",
                    tint = Color.White,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            if (movieLink != null) {
                                navToYTPLayer(movieLink)
                            } else {
                                navToSeasonsScreen(movieId)
                            }
                        }
                )
            }
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = "Share movie",
                tint = Color.White,
                modifier = Modifier
                    .size(30.dp)
            )
        }
    }
}


@Composable
fun MovieDescriptionText(text: String, maxHeight: Int = 100) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Column {

        var modifier = Modifier.fillMaxWidth()

        if (!expanded) {
            modifier = modifier.heightIn(max = maxHeight.dp)
        }

        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = modifier
        )

        if (!expanded && text.length > 100) {
            Button(
                onClick = { expanded = true },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                shape = MaterialTheme.shapes.small,
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    "Толығырақ",
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        } else {
            Button(
                onClick = { expanded = false },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                shape = MaterialTheme.shapes.small,
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    "Жасыру",
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}

@Composable
fun MovieAuthorsInfo(director: String, producer: String) {
    Column {
        Row {
            Text(
                text = "Режиссер:",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = director,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Row {
            Text(
                text = "Продюсер:",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = producer,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun MovieSeasonInfo(seasonCount: String, seriesCount: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Бөлімдер", color = MaterialTheme.colorScheme.onSurface)
        Row {
            Text(text = seasonCount, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(text = seriesCount, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowForward,
                contentDescription = "Arrow to seasons",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}


@Composable
fun MovieScreenshotsList(screenshotList: List<Movie.Screenshot>) {
    Text(text = "Screenshots", color = MaterialTheme.colorScheme.onSurface)
    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState())
    ) {
        screenshotList.forEach { screenshot ->
            AsyncImage(
                model = screenshot.link,
                contentDescription = "screenshot",
                clipToBounds = true,
                modifier = Modifier
                    .height(112.dp)
                    .width(184.dp)
            )
        }
    }
}

