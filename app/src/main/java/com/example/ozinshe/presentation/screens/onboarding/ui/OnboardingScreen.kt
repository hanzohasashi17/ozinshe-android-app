package com.example.ozinshe.presentation.screens.onboarding.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ozinshe.presentation.commonUi.CommonSubmitButton
import com.example.ozinshe.presentation.screens.onboarding.viewmodel.OnboardingViewModel
import com.example.ozinshe.presentation.commonUi.onboarding.PageComponent
import com.example.ozinshe.presentation.commonUi.onboarding.onboardingPages
import com.example.ozinshe.presentation.commonUi.onboarding.NextButtonComponent

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    skipToHomeScreen: () -> Unit,
    onboardingViewModel: OnboardingViewModel = hiltViewModel()
) {
    val pagerState = rememberPagerState(pageCount = { onboardingPages.size })

    fun onboardingButtonHandler(skipToHomeScreenClick: () -> Unit, onboardingViewModel: OnboardingViewModel) {
        onboardingViewModel.updateHasViewedOnboarding()
        skipToHomeScreenClick()
    }

    Box {
        HorizontalPager(state = pagerState, userScrollEnabled = false) { page ->
            PageComponent(page = onboardingPages[page])
        }
        NextButtonComponent(pagerState = pagerState)
        if (pagerState.currentPage == 2) {
            Row(modifier = Modifier.padding(top = 684.dp, start = 24.dp, end = 24.dp)) {
                CommonSubmitButton(
                    text = "Әрі қарай",
                    onClick = {
                        onboardingButtonHandler(skipToHomeScreen, onboardingViewModel)
                    })
            }
        }
    }
}