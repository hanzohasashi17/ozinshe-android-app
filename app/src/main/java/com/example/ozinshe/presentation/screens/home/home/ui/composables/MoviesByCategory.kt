package com.example.ozinshe.presentation.screens.home.home.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ozinshe.presentation.composables.MovieItemByCategory
import com.example.ozinshe.presentation.composables.MovieListByCategory
import com.example.ozinshe.presentation.screens.home.home.state.MovieByCategoryState
import com.example.ozinshe.presentation.utils.getMovieType
import com.example.ozinshe.presentation.utils.shimmerLoader

@Composable
fun MoviesByCategory(
    moviesByCategoryState: MovieByCategoryState,
    navToMovieInfoScreen: (movieId: String) -> Unit,
    navToMovieListScreen: (categoryId: Int) -> Unit
) {
    when (moviesByCategoryState) {
        is MovieByCategoryState.Success -> {
            val moviesByCategoryState = moviesByCategoryState

            Column {
                moviesByCategoryState.moviesByCategory.forEach { category ->
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = category.categoryName,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Button(
                            onClick = { navToMovieListScreen(category.categoryId) },
                            colors = ButtonColors(
                                containerColor = Color.Transparent,
                                disabledContentColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent,
                                contentColor = MaterialTheme.colorScheme.primary
                            ),
                            contentPadding = ButtonDefaults.TextButtonContentPadding.also { 0.dp }
                        ) {
                            Text(
                                text = "Барлығы",
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    MovieListByCategory(
                        movieListByCategory = category.movies,
                        navToMovieInfoScreen = navToMovieInfoScreen
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }

        is MovieByCategoryState.Error -> {
            val errorState = moviesByCategoryState as MovieByCategoryState.Error
            // Здесь вы можете обработать ошибку errorState.error
        }

        MovieByCategoryState.Initial -> {
            // Здесь вы можете отобразить начальное состояние
        }

        MovieByCategoryState.Loading -> {
            repeat(2) {
                Spacer(
                    modifier = Modifier
                        .width(150.dp)
                        .height(25.dp)
                        .shimmerLoader()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    repeat(3) {
                        Column {
                            Spacer(
                                modifier = Modifier
                                    .width(112.dp)
                                    .height(164.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .shimmerLoader()
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Spacer(
                                modifier = Modifier
                                    .width(112.dp)
                                    .height(20.dp)
                                    .shimmerLoader()
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Spacer(
                                modifier = Modifier
                                    .width(112.dp)
                                    .height(20.dp)
                                    .shimmerLoader()
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}