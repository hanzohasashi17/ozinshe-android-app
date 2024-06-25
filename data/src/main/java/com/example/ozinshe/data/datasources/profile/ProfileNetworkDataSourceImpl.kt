package com.example.ozinshe.data.datasources.profile

import com.example.ozinshe.data.models.ProfileDataDTO
import com.example.ozinshe.data.models.UserProfile
import com.example.ozinshe.data.services.ProfileService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileNetworkDataSourceImpl @Inject constructor(
    private val profileService: ProfileService
): ProfileNetworkDataSource {
    override suspend fun getProfileData(): UserProfile {
        return withContext(Dispatchers.IO) {
            profileService.getProfileData()
        }
    }

    override suspend fun updateProfileData(profileDataDTO: ProfileDataDTO): UserProfile {
        return withContext(Dispatchers.IO) {
            profileService.updateProfileData(profileDataDTO)
        }
    }

}