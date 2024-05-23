package com.example.ozinshe.presentation.screens.home.season.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ozinshe.presentation.screens.home.season.viewmodel.SeasonScreenViewModel
import com.example.ozinshe.presentation.utils.HorizontalLine
import com.example.ozinshe.presentation.utils.toPortraitOrientation


@Composable
fun SeasonsScreen(
    movieId: Int?,
    navToYTPlayer: (String) -> Unit,
    viewModel: SeasonScreenViewModel = hiltViewModel()
) {
    toPortraitOrientation()

    LaunchedEffect(Unit) {
        viewModel.fetchMovieSeasons(movieId = movieId!!)
    }

    val seasons = viewModel.movieSeasons.value
    val episodes = seasons.firstOrNull()?.videos?.map {
        it
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                IconButton(
                    onClick = {  }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = null,
                    )
                }
                Text(text = "")
            }
            Text(
                text = "Бөлімдер",
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .wrapContentSize(Alignment.Center)
            )
        }
        HorizontalLine()
        LazyRow(
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            items(seasons) {
                Button(
                    onClick = {  }

                ) {
                    Text(text = it.number.toString())
                }
            }
        }
        LazyColumn(
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            if (episodes != null) {
                items(episodes) {
                    Button(
                        onClick = {
                            navToYTPlayer(it.link)
                        }
                    ) {
                        Text(
                            text = it.number.toString()
                        )
                    }
                }
            }
        }
    }
}