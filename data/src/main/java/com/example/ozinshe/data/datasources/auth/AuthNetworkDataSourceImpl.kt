package com.example.ozinshe.data.datasources.auth

import com.example.ozinshe.data.models.AuthRequest
import com.example.ozinshe.data.models.User
import com.example.ozinshe.data.services.AuthService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class AuthNetworkDataSourceImpl @Inject constructor(
    private val authService: AuthService
) : AuthNetworkDataSource {
    override suspend fun signUp(body: AuthRequest): User {
        return withContext(Dispatchers.IO) {
            authService.signUp(body)
        }
    }

    override suspend fun signIn(body: AuthRequest): User {
        return withContext(Dispatchers.IO) {
            authService.signIn(body)
        }
    }
}