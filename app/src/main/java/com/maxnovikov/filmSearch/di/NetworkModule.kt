package com.maxnovikov.filmSearch.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.maxnovikov.filmSearch.data.network.FilmApi
import com.maxnovikov.filmSearch.data.network.FilmRepositoryImpl
import com.maxnovikov.filmSearch.domain.FilmRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

  companion object {

    @Provides
    fun provideFilmApi(): FilmApi = Retrofit.Builder()
      .baseUrl("https://kinopoiskapiunofficial.tech")
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