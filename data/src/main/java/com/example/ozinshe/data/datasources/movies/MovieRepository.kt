package com.example.ozinshe.data.datasources.movies

import com.example.ozinshe.data.models.FavoriteMovieDTO
import com.example.ozinshe.data.models.MainMovie
import com.example.ozinshe.data.models.Movie
import com.example.ozinshe.data.models.MovieByCategory
import com.example.ozinshe.data.models.MoviesByCategoryResult
import com.example.ozinshe.data.models.Season

interface MovieRepository {
    suspend fun getMainMovies(): List<MainMovie>

    suspend fun getMoviesByCategory(): List<MovieByCategory>

    suspend fun getMovieById(id: Int): Movie

    suspend fun getMovieSeasons(id: Int): List<Season>

    suspend fun getMoviesByCategoryId(categoryId: Int): MoviesByCategoryResult

    suspend fun searchMovies(text: String): List<Movie>

    suspend fun getMovieCategories(): List<Movie.Category>

    suspend fun getFavoriteMovies(userId: Int): List<Movie>

    suspend fun addFavoriteMovie(movieId: FavoriteMovieDTO): FavoriteMovieDTO
}



