package com.example.ozinshe.presentation.screens.navigation.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ozinshe.presentation.screens.favorite.ui.FavoriteScreen
import com.example.ozinshe.presentation.screens.home.home.ui.HomeScreen
import com.example.ozinshe.presentation.screens.home.home.viewmodel.HomeScreenViewModel
import com.example.ozinshe.presentation.screens.home.movie_info_screen.ui.MovieInfoScreen
import com.example.ozinshe.presentation.screens.home.movie_list.ui.MovieListScreen
import com.example.ozinshe.presentation.screens.home.season.ui.SeasonsScreen
import com.example.ozinshe.presentation.screens.home.ytplayer.YTPlayer
import com.example.ozinshe.presentation.screens.navigation.Screens
import com.example.ozinshe.presentation.screens.navigation.listOfNavItems
import com.example.ozinshe.presentation.screens.navigation.navBarShowScreenList
import com.example.ozinshe.presentation.screens.profile.ui.ProfileDataScreen
import com.example.ozinshe.presentation.screens.profile.ui.ProfileScreen
import com.example.ozinshe.presentation.screens.search.ui.SearchScreen


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun AppNavigation(
    navToLoginScreen: () -> Unit
) {
    val navController = rememberNavController()
    val showNavBar =
        navController.currentBackStackEntryAsState().value?.destination?.route in navBarShowScreenList.map { it }

    Scaffold(
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        containerColor = MaterialTheme.colorScheme.surface,
        bottomBar = {
            if (showNavBar) {
                BottomAppBar {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    listOfNavItems.forEach { navItem ->
                        NavigationBarItem(
                            selected = currentDestination?.hierarchy?.any { it.route == navItem.route } == true,
                            onClick = {
                                navController.navigate(navItem.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true

                                }
                            },
                            icon = {
                                Icon(imageVector = navItem.icon, contentDescription = null)
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.HomeScreen.name,
            modifier = Modifier.padding(paddingValues),
        ) {
            composable(
                route = Screens.HomeScreen.name
            ) {
                HomeScreen(
                    navToMovieListScreen = { navController.navigate("${Screens.MovieListScreen.name}/${it}") },
                    navToMovieInfoScreen = { navController.navigate("${Screens.MovieInfoScreen.name}/${it}") }
                )
            }
            composable(
                route = Screens.SearchScreen.name
            ) {
                SearchScreen()
            }
            composable(
                route = Screens.FavoriteScreen.name
            ) {
                FavoriteScreen()
            }
            composable(
                route = Screens.ProfileScreen.name
            ) {
                ProfileScreen(
                    navToLoginScreen = navToLoginScreen,
                    navToProfileDataScreen = { navController.navigate(Screens.ProfileDataScreen.name) }
                )
            }
            composable(
                route = "${Screens.MovieInfoScreen.name}/{movieId}",
                arguments = listOf(navArgument("movieId") { type = NavType.StringType })
            ) { it ->
                MovieInfoScreen(
                    movieId = it.arguments?.getString("movieId"),
                    navToYTPlayer = { navController.navigate("${Screens.YTPlayer.name}/${it}") },
                    navToSeasonsScreen = { navController.navigate("${Screens.SeasonScreen.name}/${it}") }
                )
            }
            composable(
                route = "${Screens.YTPlayer.name}/{movieLink}",
                arguments = listOf(navArgument("movieLink") { type = NavType.StringType })
            ) {
                YTPlayer(
                    movieLink = it.arguments?.getString("movieLink")
                )
            }
            composable(
                route = "${Screens.SeasonScreen.name}/{movieId}",
                arguments = listOf(navArgument("movieId") { type = NavType.IntType })
            ) { it ->
                SeasonsScreen(
                    movieId = it.arguments?.getInt("movieId"),
                    navToYTPlayer = { navController.navigate("${Screens.YTPlayer.name}/${it}") }
                )
            }
            composable(
                route = "${Screens.MovieListScreen.name}/{categoryId}",
                arguments = listOf(navArgument("categoryId") { type = NavType.IntType })
            ) { it ->
                MovieListScreen(
                    categoryId = it.arguments?.getInt("categoryId"),
                    navToInfoScreen = { navController.navigate("${Screens.MovieInfoScreen.name}/${it}") }
                )
            }
            composable(
                route = Screens.ProfileDataScreen.name
            ) {
                ProfileDataScreen()
            }
        }
    }
}