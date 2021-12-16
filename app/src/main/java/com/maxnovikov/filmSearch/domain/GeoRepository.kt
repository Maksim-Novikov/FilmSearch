package com.maxnovikov.filmSearch.domain

import com.maxnovikov.filmSearch.domain.entity.City

interface GeoRepository {

  suspend fun getCity(lat: Double, lon: Double): City
}
