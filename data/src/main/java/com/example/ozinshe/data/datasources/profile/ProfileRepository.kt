package com.example.ozinshe.data.datasources.profile

import com.example.ozinshe.data.models.ProfileDataDTO
import com.example.ozinshe.data.models.UserProfile

interface ProfileRepository {
    suspend fun getProfileData(): UserProfile
    suspend fun updateProfileData(profileDataDTO: ProfileDataDTO): UserProfile
}