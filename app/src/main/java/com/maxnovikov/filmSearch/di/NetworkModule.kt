package com.maxnovikov.filmSearch.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.maxnovikov.filmSearch.data.network.FilmApi
import com.maxnovikov.filmSearch.data.network.FilmRepositoryImpl
import com.maxnovikov.filmSearch.domain.FilmRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create

object NetworkModule {

  private val filmApi: FilmApi = Retrofit.Builder()
    .baseUrl("https://kinopoiskapiunofficial.tech")
    .addConverterFactory(
      Json(builderAction = {
        isLenient = true
        ignoreUnknownKeys = true
      }).asConverterFactory("application/json".toMediaType())
    )
    .build()
    .create()

  private var repository: FilmRepository? = null

  fun getRepository(): FilmRepository =
    repository ?: FilmRepositoryImpl(filmApi).also { repository = it }
}