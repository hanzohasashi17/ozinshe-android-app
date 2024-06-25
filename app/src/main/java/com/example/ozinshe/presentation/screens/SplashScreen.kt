package com.example.ozinshe.presentation.screens

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ozinshe.R
import com.example.ozinshe.presentation.screens.auth.viewmodel.AuthViewModel
import com.example.ozinshe.presentation.screens.home.home.viewmodel.HomeScreenViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    goToLogin: () -> Unit,
    goToHomeScreen: () -> Unit,
    homeViewModel: HomeScreenViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val loginState by authViewModel.userTokenState.collectAsState()
    val movies by homeViewModel.moviesByCategoryState.collectAsState()

    LaunchedEffect(Unit) {
        if(loginState.isLogged) {
            homeViewModel.fetchMainMovies()
            homeViewModel.fetchMoviesByCategory()
            delay(2500)
            goToHomeScreen()
        } else {
            delay(2000)
            goToLogin()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEAD1FD))
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            drawCircle(
                color = Color(0xFFD1A4FC),
                radius = 1500f,
                center = Offset(-100f, 1500f)
            )
            drawCircle(
                color = Color(0xFF5415C6),
                radius = 1500f,
                center = Offset(1600f, 1700f)
            )
            drawCircle(
                color = Color(0xFF3E0FA6),
                radius = 1500f,
                center = Offset(550f, 2200f)
            )
        }
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.splash_logo),
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center),
            tint = Color(0xFFF9FAFB)
        )
    }
}