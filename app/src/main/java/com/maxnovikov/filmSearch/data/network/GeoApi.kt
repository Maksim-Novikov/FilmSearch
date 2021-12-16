package com.maxnovikov.filmSearch.data.network

import com.maxnovikov.filmSearch.data.network.entity.city.GetCityResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoApi {

  @GET("reverse")
  suspend fun getCity(
    @Query("lat") lat: Double,
    @Query("lon") lon: Double,
    @Query("format") format: String = "json"
  ): GetCityResponse
}