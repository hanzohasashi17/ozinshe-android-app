package com.example.ozinshe.data.datasources.movies

import com.example.ozinshe.data.datasources.movies.MovieNetworkDataSource
import com.example.ozinshe.data.models.FavoriteMovieDTO
import com.example.ozinshe.data.models.MainMovie
import com.example.ozinshe.data.models.Movie
import com.example.ozinshe.data.models.MovieByCategory
import com.example.ozinshe.data.models.MoviesByCategoryResult
import com.example.ozinshe.data.models.Season
import com.example.ozinshe.data.models.ServerResponse
import com.example.ozinshe.data.services.MovieService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieNetworkDataSourceImpl @Inject constructor(
    private val movieService: MovieService
) : MovieNetworkDataSource {

    override suspend fun getMainMovies(): List<MainMovie> {
        return withContext(Dispatchers.IO) {
            movieService.getMainMovies()
        }
    }

    override suspend fun getMoviesByCategory(): List<MovieByCategory> {
        return withContext(Dispatchers.IO) {
            movieService.getMoviesByCategory()
        }
    }

    override suspend fun getMovieById(id: Int): Movie {
        return withContext(Dispatchers.IO) {
            movieService.getMoviesById(id)
        }
    }

    override suspend fun getMovieSeasons(id: Int): List<Season> {
        return withContext(Dispatchers.IO) {
            movieService.getMovieSeasons(id)
        }
    }

    override suspend fun getMoviesByCategoryId(categoryId: Int): MoviesByCategoryResult {
        return withContext(Dispatchers.IO) {
            movieService.getMoviesByCategoryId(categoryId)
        }
    }

    override suspend fun searchMovies(text: String): List<Movie> {
        return withContext(Dispatchers.IO) {
            movieService.searchMovies(text)
        }
    }

    override suspend fun getMovieCategories(): List<Movie.Category> {
        return withContext(Dispatchers.IO) {
            movieService.getMovieCategories()
        }
    }

    override suspend fun getFavoriteMovies(userId: Int): List<Movie> {
        return withContext(Dispatchers.IO) {
            movieService.getFavoriteMovies(userId)
        }
    }

    override suspend fun addFavoriteMovie(movieId: FavoriteMovieDTO): FavoriteMovieDTO {
        return withContext(Dispatchers.IO) {
            movieService.addFavoriteMovie(movieId)
        }
    }
}