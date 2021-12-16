package com.maxnovikov.filmSearch.data.network

import com.maxnovikov.filmSearch.domain.GeoRepository
import com.maxnovikov.filmSearch.domain.entity.City
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GeoRepositoryImpl @Inject constructor(
  private val geoApi: GeoApi
) : GeoRepository {

  override suspend fun getCity(lat: Double, lon: Double): City = withContext(Dispatchers.IO) {
    val response = geoApi.getCity(lat, lon)
    City(
      name = response.address?.city ?: "",
      country = response.address?.country ?: ""
    )
  }
}