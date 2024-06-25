package com.example.ozinshe.presentation.screens.home.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ozinshe.R
import com.example.ozinshe.presentation.screens.home.home.ui.composables.MainMovies
import com.example.ozinshe.presentation.screens.home.home.ui.composables.MoviesByCategory
import com.example.ozinshe.presentation.screens.home.home.viewmodel.HomeScreenViewModel

@Composable
fun HomeScreen(
    navToMovieListScreen: (categoryId: Int?) -> Unit,
    navToMovieInfoScreen: (movieId: String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 24.dp, vertical = 60.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_1),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(2.dp))
            Image(
                painter = painterResource(id = R.drawable.logo_2),
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        MainMovies()
        Spacer(modifier = Modifier.height(20.dp))
        MoviesByCategory(
            navToMovieInfoScreen = navToMovieInfoScreen,
            navToMovieListScreen = navToMovieListScreen
        )
    }
}

