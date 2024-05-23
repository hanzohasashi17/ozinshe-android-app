package com.example.ozinshe.data.repositories

import com.example.ozinshe.data.datasources.movies.MovieNetworkDataSourceImpl
import com.example.ozinshe.data.datasources.movies.MovieRepository
import com.example.ozinshe.data.models.FavoriteMovieDTO
import com.example.ozinshe.data.models.MainMovie
import com.example.ozinshe.data.models.Movie
import com.example.ozinshe.data.models.MovieByCategory
import com.example.ozinshe.data.models.MoviesByCategoryResult
import com.example.ozinshe.data.models.Season
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieNetworkDataSourceImpl: MovieNetworkDataSourceImpl,
) : MovieRepository {
    override suspend fun getMainMovies(): List<MainMovie> {
        return  movieNetworkDataSourceImpl.getMainMovies()
    }

    override suspend fun getMoviesByCategory(): List<MovieByCategory> {
        return movieNetworkDataSourceImpl.getMoviesByCategory()
    }

    override suspend fun getMovieById(id: Int): Movie {
        return movieNetworkDataSourceImpl.getMovieById(id)
    }

    override suspend fun getMovieSeasons(id: Int): List<Season> {
        return movieNetworkDataSourceImpl.getMovieSeasons(id)
    }

    override suspend fun getMoviesByCategoryId(categoryId: Int): MoviesByCategoryResult {
        return movieNetworkDataSourceImpl.getMoviesByCategoryId(categoryId)
    }

    override suspend fun searchMovies(text: String): List<Movie> {
        return movieNetworkDataSourceImpl.searchMovies(text)
    }

    override suspend fun getMovieCategories(): List<Movie.Category> {
        return movieNetworkDataSourceImpl.getMovieCategories()
    }

    override suspend fun getFavoriteMovies(userId: Int): List<Movie> {
        return movieNetworkDataSourceImpl.getFavoriteMovies(userId)
    }

    override suspend fun addFavoriteMovie(movieId: FavoriteMovieDTO): FavoriteMovieDTO {
        return movieNetworkDataSourceImpl.addFavoriteMovie(movieId)
    }
}