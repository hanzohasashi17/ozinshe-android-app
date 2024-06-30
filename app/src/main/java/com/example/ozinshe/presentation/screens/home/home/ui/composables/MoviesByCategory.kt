package com.example.ozinshe.presentation.screens.home.home.ui.composables

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ozinshe.presentation.commonUi.MovieListByCategory
import com.example.ozinshe.presentation.screens.home.home.viewmodel.HomeScreenViewModel

@Composable
fun MoviesByCategory(
    navToMovieInfoScreen: (movieId: String) -> Unit,
    navToMovieListScreen: (categoryId: Int) -> Unit,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val moviesState by viewModel.moviesByCategoryState.collectAsState()

    LaunchedEffect(moviesState.movieList) {
        if (moviesState.movieList.isNullOrEmpty()) {
            viewModel.fetchMoviesByCategory()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Log.d("check", moviesState.movieList.toString())
        moviesState.movieList?.forEach { category ->
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