package com.example.ozinshe.presentation.screens.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe.data.datasources.localStorage.SharedPreferencesManager
import com.example.ozinshe.presentation.screens.auth.state.UserTokenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager
) : ViewModel() {

    private val _userTokenState = MutableStateFlow(UserTokenState())
    val userTokenState = _userTokenState.asStateFlow()

    init {
        getToken()
    }

    private fun getToken() {
        viewModelScope.launch {
            try {
                val token = sharedPreferencesManager.getToken()
                _userTokenState.update { currentState ->
                    currentState.copy(isLogged = parseTokenValueToBoolean(token))
                }
            } catch (e: Exception) {
                _userTokenState.update { currentState ->
                    currentState.copy(errorMessage = e.message)
                }
            }
        }
    }

    fun updateLoginStatus() {
        viewModelScope.launch {
            try {
                sharedPreferencesManager.deleteToken()
                _userTokenState.update { currentState ->
                    currentState.copy(isLogged = false)
                }
            } catch (e: Exception) {
                _userTokenState.update { currentState ->
                    currentState.copy(errorMessage = e.message)
                }
            }
        }
    }

}

fun parseTokenValueToBoolean(token: String?): Boolean {
    return if (token.isNullOrEmpty()) {
        false
    } else {
        true
    }
}



