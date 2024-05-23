package com.example.ozinshe.presentation.screens.home.movie_list.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ozinshe.presentation.screens.search.state.SearchCategoriesState
import com.example.ozinshe.presentation.screens.search.ui.components.SearchCategories
import com.example.ozinshe.presentation.utils.HeaderTitle

@Composable
fun SearchCategoriesComponent(categoriesState: SearchCategoriesState) {
    Column {
        HeaderTitle(text = "Санаттар")

        when {
            categoriesState.isLoading -> {
                Box(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }

            categoriesState.errorMessage != null -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = categoriesState.errorMessage!!,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.TopCenter),
                        color = MaterialTheme.colorScheme.onError
                    )
                }
            }

            categoriesState.categories?.isNotEmpty() == true -> {
                SearchCategories(categories = categoriesState.categories)
            }
        }
    }
}