package com.maxnovikov.filmSearch.data.network.entity.city

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Address(
  @SerialName("city")
  val city: String? = null,
  @SerialName("city_district")
  val cityDistrict: String? = null,
  @SerialName("country")
  val country: String? = null,
  @SerialName("country_code")
  val countryCode: String? = null,
  @SerialName("county")
  val county: String? = null,
  @SerialName("house_number")
  val houseNumber: String? = null,
  @SerialName("postcode")
  val postcode: String? = null,
  @SerialName("region")
  val region: String? = null,
  @SerialName("road")
  val road: String? = null,
  @SerialName("state")
  val state: String? = null
)