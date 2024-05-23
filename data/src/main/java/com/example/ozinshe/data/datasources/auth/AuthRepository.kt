package com.example.ozinshe.data.datasources.auth

import com.example.ozinshe.data.models.AuthRequest
import com.example.ozinshe.data.models.ServerResponse
import com.example.ozinshe.data.models.User

interface AuthRepository {
    suspend fun registrationUser(body: AuthRequest): User
}