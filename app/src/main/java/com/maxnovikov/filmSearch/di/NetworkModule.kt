package com.maxnovikov.filmSearch.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.maxnovikov.filmSearch.data.network.FilmApi
import com.maxnovikov.filmSearch.data.network.FilmApi.Companion.X_API_KEY
import com.maxnovikov.filmSearch.data.network.FilmRepositoryImpl
import com.maxnovikov.filmSearch.domain.FilmRepository
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

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

  companion object {

    @Provides
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
  }

  @Binds
  abstract fun getRepository(filmRepositoryImpl: FilmRepositoryImpl): FilmRepository
}