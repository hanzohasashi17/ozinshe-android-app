package com.example.ozinshe.data.services

import com.example.ozinshe.data.datasources.auth.AuthInterceptor
import com.example.ozinshe.data.models.FavoriteMovieDTO
import com.example.ozinshe.data.models.MainMovie
import com.example.ozinshe.data.models.Movie
import com.example.ozinshe.data.models.MovieByCategory
import com.example.ozinshe.data.models.MoviesByCategoryResult
import com.example.ozinshe.data.models.Season
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @Headers("Content-Type: application/json")
    @GET("core/V1/movies_main")
    suspend fun getMainMovies(): List<MainMovie>

    @Headers("Content-Type: application/json")
    @GET("core/V1/movies/main")
    suspend fun getMoviesByCategory(): List<MovieByCategory>

    @Headers("Content-Type: application/json")
    @GET("core/V1/movies/{id}")
    suspend fun getMoviesById(@Path("id") id: Int): Movie

    @Headers("Content-Type: application/json")
    @GET("core/V1/seasons/{id}")
    suspend fun getMovieSeasons(@Path("id") id: Int): List<Season>

    @Headers("Content-Type: application/json")
    @GET("core/V1/movies/page")
    suspend fun getMoviesByCategoryId(@Query("categoryId") categoryId: Int): MoviesByCategoryResult

    @Headers("Content-Type: application/json")
    @GET("core/V1/movies/search")
    suspend fun searchMovies(@Query("search") text: String): List<Movie>

    @Headers("Content-Type: application/json")
    @GET("core/V1/categories")
    suspend fun getMovieCategories(): List<Movie.Category>

    @Headers("Content-Type: application/json")
    @GET("core/V1/favorite/")
    suspend fun getFavoriteMovies(@Query("userId") userId: Int): List<Movie>

    @Headers("Content-Type: application/json")
    @POST("core/V1/favorite")
    suspend fun addFavoriteMovie(@Body movieId: FavoriteMovieDTO): FavoriteMovieDTO
}