package com.example.ozinshe.data.models


import com.google.gson.annotations.SerializedName

data class MovieByCategory(
    @SerializedName("categoryId")
    val categoryId: Int,
    @SerializedName("categoryName")
    val categoryName: String,
    @SerializedName("movies")
    val movies: List<Movie>
)
