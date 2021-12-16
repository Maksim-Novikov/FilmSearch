package com.maxnovikov.filmSearch.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.maxnovikov.filmSearch.data.network.FilmApi
import com.maxnovikov.filmSearch.data.network.FilmApi.Companion.X_API_KEY
import com.maxnovikov.filmSearch.data.network.FilmRepositoryImpl
import com.maxnovikov.filmSearch.data.network.GeoApi
import com.maxnovikov.filmSearch.data.network.GeoRepositoryImpl
import com.maxnovikov.filmSearch.domain.FilmRepository
import com.maxnovikov.filmSearch.domain.GeoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor.Chain
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

  companion object {

    @Provides
    @Singleton
    fun provideFilmApi(): FilmApi = Retrofit.Builder()
      .baseUrl("https://kinopoiskapiunofficial.tech")
      .client(
        OkHttpClient.Builder()
          .addInterceptor { chain: Chain ->
            val request = chain.request()
              .newBuilder()
              .header("X-API-KEY", X_API_KEY)
              .build()

            chain.proceed(request)
          }
          .addInterceptor(
            HttpLoggingInterceptor()
              .setLevel(HttpLoggingInterceptor.Level.BODY)
          )
          .connectTimeout(10, SECONDS)
          .callTimeout(10, SECONDS)
          .readTimeout(10, SECONDS)
          .writeTimeout(10, SECONDS)
          .build()
      )
      .addConverterFactory(
        Json(builderAction = {
          isLenient = true
          ignoreUnknownKeys = true
        }).asConverterFactory("application/json".toMediaType())
      )
      .build()
      .create()

    @Provides
    @Singleton
    fun provideGeoApi(): GeoApi = Retrofit.Builder()
      .baseUrl("https://nominatim.openstreetmap.org")
      .client(
        OkHttpClient.Builder()
          .addInterceptor(
            HttpLoggingInterceptor()
              .setLevel(HttpLoggingInterceptor.Level.BODY)
          )
          .connectTimeout(10, SECONDS)
          .callTimeout(10, SECONDS)
          .readTimeout(10, SECONDS)
          .writeTimeout(10, SECONDS)
          .build()
      )
      .addConverterFactory(
        Json(builderAction = {
          isLenient = true
          ignoreUnknownKeys = true
        }).asConverterFactory("application/json".toMediaType())
      )
      .build()
      .create()
  }

  @Binds
  @Singleton
  abstract fun getRepository(filmRepositoryImpl: FilmRepositoryImpl): FilmRepository

  @Binds
  @Singleton
  abstract fun getGeoRepository(geoRepositoryImpl: GeoRepositoryImpl): GeoRepository
}