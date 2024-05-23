package com.example.ozinshe.presentation.screens.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ozinshe.data.datasources.localStorage.SharedPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    val sharedPreferencesManager: SharedPreferencesManager
): ViewModel() {

    private val _hasViewedOnboarding = MutableStateFlow(sharedPreferencesManager.getViewedOnboarding())
    val hasViewedOnboarding: StateFlow<Boolean> = _hasViewedOnboarding.asStateFlow()


    fun updateHasViewedOnboarding() {
        sharedPreferencesManager.saveViewedOnboarding()
        _hasViewedOnboarding.value = true
    }
}