package com.example.ozinshe.presentation.screens.profile.viewmodel

import android.util.Log
import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe.data.models.ProfileDataDTO
import com.example.ozinshe.data.repositories.ProfileRepositoryImpl
import com.example.ozinshe.presentation.screens.profile.state.ProfileState
import com.example.ozinshe.presentation.screens.profile.state.ProfileUpdateState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepositoryImpl: ProfileRepositoryImpl
) : ViewModel() {

    private var _profileState = MutableStateFlow(ProfileState())
    val profileState = _profileState.asStateFlow()

    private var _profileUpdateState = MutableStateFlow(ProfileUpdateState())
    val profileUpdateState = _profileUpdateState.asStateFlow()

    init {
        fetchProfileData()
    }

    private fun fetchProfileData() {
        _profileState.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            try {
                val data = profileRepositoryImpl.getProfileData()
                _profileState.update { it.copy(isLoading = false, userData = data) }
            } catch (e: Exception) {
                _profileState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    fun updateProfileData(name: String, phoneNumber: String, birthDate: String?) {
        _profileUpdateState.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            try {
                val date = Date()
                val updatedResult = profileRepositoryImpl.updateProfileData(ProfileDataDTO(
                    name = name,
                    phoneNumber = phoneNumber,
                    birthDate = SimpleDateFormat(birthDate, java.util.Locale.getDefault()).format(date),
                    language = ""))
                _profileState.update { it.copy(userData = updatedResult) }
            } catch (e: Exception) {
                _profileUpdateState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }
}