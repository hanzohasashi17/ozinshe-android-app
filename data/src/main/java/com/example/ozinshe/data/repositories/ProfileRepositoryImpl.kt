package com.example.ozinshe.data.repositories

import com.example.ozinshe.data.datasources.profile.ProfileNetworkDataSourceImpl
import com.example.ozinshe.data.datasources.profile.ProfileRepository
import com.example.ozinshe.data.models.ProfileDataDTO
import com.example.ozinshe.data.models.UserProfile
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileNetworkDataSourceImpl: ProfileNetworkDataSourceImpl
) : ProfileRepository {
    override suspend fun getProfileData(): UserProfile {
        return profileNetworkDataSourceImpl.getProfileData()
    }

    override suspend fun updateProfileData(profileDataDTO: ProfileDataDTO): UserProfile {
        return profileNetworkDataSourceImpl.updateProfileData(profileDataDTO)
    }
}