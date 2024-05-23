package com.example.ozinshe.presentation.screens.navigation

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

val listOfNavItems: List<NavItem> = listOf(
    NavItem(
        label = "Home",
        icon = Icons.Default.Home,
        route = Screens.HomeScreen.name
    ),
    NavItem(
        label = "Search",
        icon = Icons.Default.Search,
        route = Screens.SearchScreen.name
    ),
    NavItem(
        label = "Favorite",
        icon = Icons.Default.Favorite,
        route = Screens.FavoriteScreen.name
    ),
    NavItem(
        label = "Profile",
        icon = Icons.Default.AccountCircle,
        route = Screens.ProfileScreen.name
    )
)

val navBarShowScreenList: List<String> = listOf(
    Screens.HomeScreen.name,
    Screens.SearchScreen.name,
    Screens.FavoriteScreen.name,
    Screens.ProfileScreen.name,
    "${ Screens.MovieInfoScreen.name }/{movieId}"
)