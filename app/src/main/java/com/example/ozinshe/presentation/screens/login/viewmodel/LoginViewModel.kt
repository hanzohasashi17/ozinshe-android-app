package com.example.ozinshe.presentation.screens.login.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe.data.datasources.localStorage.SharedPreferencesManager
import com.example.ozinshe.data.models.AuthRequest
import com.example.ozinshe.data.repositories.AuthRepositoryImpl
import com.example.ozinshe.presentation.screens.login.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepositoryImpl: AuthRepositoryImpl,
    private val sharedPreferencesManager: SharedPreferencesManager
) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.update { currentState ->
                currentState.copy(isLoading = true, errorMessage = null)
            }
            try {
                val user = authRepositoryImpl.loginUser(AuthRequest(email, password))
                Log.d("AA", user.toString())
                _loginState.update {
                    it.copy(isLoading = false, user = user)
                }
                Log.d("aaa", user.toString())
                sharedPreferencesManager.saveToken(user.accessToken)
                sharedPreferencesManager.saveUserId(user.id)
            } catch (e: Exception) {
                _loginState.update {
                    it.copy(isLoading = false, errorMessage = e.message)
                }
            }
        }
    }
}