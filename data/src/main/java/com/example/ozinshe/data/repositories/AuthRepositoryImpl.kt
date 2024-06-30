package com.example.ozinshe.data.repositories

import com.example.ozinshe.data.datasources.auth.AuthNetworkDataSourceImpl
import com.example.ozinshe.data.datasources.auth.AuthRepository
import com.example.ozinshe.data.models.AuthRequest
import com.example.ozinshe.data.models.User
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authNetworkDataSourceImpl: AuthNetworkDataSourceImpl,
) : AuthRepository {
    override suspend fun registrationUser(body: AuthRequest): User {
        return authNetworkDataSourceImpl.signUp(body)
    }

    override suspend fun loginUser(body: AuthRequest): User {
        return authNetworkDataSourceImpl.signIn(body)
    }
}
