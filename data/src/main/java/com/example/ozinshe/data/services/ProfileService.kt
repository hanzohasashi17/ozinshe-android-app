package com.example.ozinshe.data.services

import com.example.ozinshe.data.models.ProfileDataDTO
import com.example.ozinshe.data.models.UserProfile
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PUT

interface ProfileService {
    @Headers("Content-Type: application/json")
    @GET("core/V1/user/profile")
    suspend fun getProfileData(): UserProfile

    @Headers("Content-Type: application/json")
    @PUT("core/V1/user/profile/")
    suspend fun updateProfileData(@Body profileData: ProfileDataDTO): UserProfile
}