package com.example.ozinshe.presentation.screens.registration.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe.data.datasources.localStorage.SharedPreferencesManager
import com.example.ozinshe.data.models.AuthRequest
import com.example.ozinshe.data.repositories.AuthRepositoryImpl
import com.example.ozinshe.presentation.screens.registration.state.RegistrationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val authRepositoryImpl: AuthRepositoryImpl,
    private val sharedPreferencesManager: SharedPreferencesManager
) : ViewModel() {

    private val _registrationState = MutableStateFlow(RegistrationState())
    val registrationState = _registrationState.asStateFlow()

    fun sendRegisterData(email: String, password: String, repeat: String) {
        if (email.isNotEmpty() && password == repeat) {
            _registrationState.update { currentState ->
                currentState.copy(isLoading = true, errorMessage = null)
            }
            viewModelScope.launch {
                try {
                    val user = authRepositoryImpl.registrationUser(AuthRequest(email, password))
                    _registrationState.update { it.copy(isLoading = false, user = user)
                    }
                    Log.d("aaa", user.toString())
                    sharedPreferencesManager.saveToken(user.accessToken)
                    sharedPreferencesManager.saveUserId(user.id)
                } catch (e: Exception) {
                    _registrationState.update { it.copy(isLoading = false, errorMessage = e.message)
                    }
                }

            }
        }
    }
}