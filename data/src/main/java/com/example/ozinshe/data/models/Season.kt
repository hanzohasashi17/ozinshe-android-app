package com.example.ozinshe.data.models


import com.google.gson.annotations.SerializedName


data class Season(
    @SerializedName("id")
    val id: Int,
    @SerializedName("movieId")
    val movieId: Int,
    @SerializedName("number")
    val number: Int,
    @SerializedName("videos")
    val videos: List<Episode>
) {
    data class Episode(
        @SerializedName("id")
        val id: Int,
        @SerializedName("link")
        val link: String,
        @SerializedName("number")
        val number: Int,
        @SerializedName("seasonId")
        val seasonId: Int
    )
}