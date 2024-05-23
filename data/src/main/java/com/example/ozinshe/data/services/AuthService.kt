package com.example.ozinshe.data.services

import com.example.ozinshe.data.models.AuthRequest
import com.example.ozinshe.data.models.User
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/V1/signup")
    suspend fun signUp(@Body body: AuthRequest): User

    @POST("auth/V1/signin")
    suspend fun signIn(@Body body: AuthRequest): User
}