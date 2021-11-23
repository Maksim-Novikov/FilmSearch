package com.maxnovikov.filmSearch.data.network

import com.maxnovikov.filmSearch.data.network.entity.TopFilmsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface FilmApi {

  @GET("/api/v2.2/films/top")
  suspend fun getTopFilms(
    @Query("type") type: String,
    @Query("page") page: Int,
    @Header("X-API-KEY") key: String = "541251fa-c296-4fc4-af05-7ae52e3e6401"
  ): TopFilmsResponse
}