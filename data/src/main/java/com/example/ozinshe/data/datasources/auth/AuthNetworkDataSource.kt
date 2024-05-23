package com.example.ozinshe.data.datasources.auth

import com.example.ozinshe.data.models.AuthRequest
import com.example.ozinshe.data.models.ServerResponse
import com.example.ozinshe.data.models.User

interface AuthNetworkDataSource {
    suspend fun signUp(body: AuthRequest): User
}