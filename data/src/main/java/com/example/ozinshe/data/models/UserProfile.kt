package com.example.ozinshe.data.models


import com.google.gson.annotations.SerializedName

data class UserProfile(
    @SerializedName("birthDate")
    val birthDate: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("language")
    val language: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String?,
    @SerializedName("user")
    val user: User
) {
    data class User(
        @SerializedName("email")
        val email: String,
        @SerializedName("id")
        val id: Int
    )
}