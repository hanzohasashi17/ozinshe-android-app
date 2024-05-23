package com.example.ozinshe.presentation

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ozinshe.presentation.screens.auth.viewmodel.AuthViewModel
import com.example.ozinshe.presentation.screens.navigation.Screens
import com.example.ozinshe.presentation.screens.navigation.ui.AppNavigation
import com.example.ozinshe.presentation.screens.onboarding.ui.OnboardingScreen
import com.example.ozinshe.presentation.screens.onboarding.viewmodel.OnboardingViewModel
import com.example.ozinshe.presentation.screens.registration.ui.RegistrationScreen
import com.example.ozinshe.presentation.theme.OzinsheTheme
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.wait

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels()
    private val onboardingViewModel: OnboardingViewModel by viewModels()


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {

        WindowCompat.setDecorFitsSystemWindows(window, false)

        super.onCreate(savedInstanceState)
        setContent {
            OzinsheTheme {
                Surface(
                    color = MaterialTheme.colorScheme.surface
                ) {
                    val navController = rememberNavController()

                    val isLoggedIn by authViewModel.userTokenState.collectAsState()
                    val hasViewedOnboarding by onboardingViewModel.hasViewedOnboarding.collectAsState()

                    NavHost(
                        navController = navController,
                        startDestination = if (!hasViewedOnboarding) {
                            Screens.OnboardingScreen.name
                        } else {
                            if (!isLoggedIn.isLogged) {
                                Screens.RegistrationScreen.name
                            } else {
                                Screens.AppNavigation.name
                            }
                        },
                    ) {
                        composable(Screens.OnboardingScreen.name) {
                            OnboardingScreen(
                                skipToHomeScreen = { navController.navigate(Screens.RegistrationScreen.name) }
                            )
                        }
                        composable(Screens.RegistrationScreen.name) {
                            RegistrationScreen(
                                onBackButtonClick = { navController.navigate(Screens.OnboardingScreen.name) },
                                signInButtonClick = { navController.navigate(Screens.AppNavigation.name) }
                            )
                        }
                        composable(Screens.AppNavigation.name) {
                            AppNavigation(
                                navToLoginScreen = { navController.navigate(Screens.RegistrationScreen.name) }
                            )
                        }
                    }
                }
            }
        }
    }
}