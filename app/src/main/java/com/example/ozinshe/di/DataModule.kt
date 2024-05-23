package com.example.ozinshe.di

import android.app.Application
import android.content.Context
import coil.ImageLoader
import coil.request.CachePolicy
import com.example.ozinshe.data.datasources.movies.MovieNetworkDataSource
import com.example.ozinshe.data.datasources.movies.MovieNetworkDataSourceImpl
import com.example.ozinshe.data.datasources.movies.MovieRepository
import com.example.ozinshe.data.datasources.localStorage.SharedPreferencesManager
import com.example.ozinshe.data.datasources.auth.AuthInterceptor
import com.example.ozinshe.data.datasources.auth.AuthNetworkDataSource
import com.example.ozinshe.data.datasources.auth.AuthNetworkDataSourceImpl
import com.example.ozinshe.data.datasources.auth.AuthRepository
import com.example.ozinshe.data.datasources.auth.TokenProvider
import com.example.ozinshe.data.repositories.AuthRepositoryImpl
import com.example.ozinshe.data.repositories.MovieRepositoryImpl
import com.example.ozinshe.data.services.AuthService
import com.example.ozinshe.data.services.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {


    // Shared Preference
    @Provides
    @Singleton
    fun provideSharedPreferencesManager(@ApplicationContext context: Context): SharedPreferencesManager {
        return SharedPreferencesManager(context)
    }











    // BASE URL
    @Provides
    fun provideBaseUrl(): String {
        return "http://api.ozinshe.com/"
    }









    // OkHttp
    @Provides
    @Singleton
    fun provideAuthInterceptor(tokenProvider: TokenProvider): AuthInterceptor {
        return AuthInterceptor(tokenProvider)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }










    // Retrofit
    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }








    // A U T H
    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthNetworkDataSourceImpl(authService: AuthService): AuthNetworkDataSource {
        return AuthNetworkDataSourceImpl(authService)
    }

    @Provides
    @Singleton
    fun provideAuthRepositoryImpl(authNetworkDataSourceImpl: AuthNetworkDataSourceImpl): AuthRepository {
        return AuthRepositoryImpl(authNetworkDataSourceImpl)
    }














    // M O V I E S
    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieNetworkDataSourceImpl(movieService: MovieService): MovieNetworkDataSource {
        return MovieNetworkDataSourceImpl(movieService)
    }

    @Provides
    @Singleton
    fun provideMovieRepositoryImpl(movieNetworkDataSourceImpl: MovieNetworkDataSourceImpl): MovieRepository {
        return MovieRepositoryImpl(movieNetworkDataSourceImpl)
    }

}
