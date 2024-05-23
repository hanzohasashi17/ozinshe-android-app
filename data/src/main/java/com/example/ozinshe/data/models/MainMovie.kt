package com.example.ozinshe.data.models


import com.google.gson.annotations.SerializedName


data class MainMovie(
    @SerializedName("fileId")
    val fileId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("link")
    val link: String,
    @SerializedName("movie")
    val movie: Movie,
    @SerializedName("sortOrder")
    val sortOrder: Int
)
