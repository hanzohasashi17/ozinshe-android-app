package com.example.ozinshe.presentation.commonUi.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NextButtonComponent(pagerState: PagerState) {
    val scope = rememberCoroutineScope()

    if(pagerState.currentPage < 2) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    scope.launch { pagerState.scrollToPage(pagerState.currentPage + 1) }
                },
                colors = ButtonDefaults.buttonColors(Color(0xFF111827)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Өткізу", color = Color.White)
            }
        }
    }
}