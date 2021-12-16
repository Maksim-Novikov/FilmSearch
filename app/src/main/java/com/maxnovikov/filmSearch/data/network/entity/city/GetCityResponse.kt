package com.maxnovikov.filmSearch.data.network.entity.city

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCityResponse(
  @SerialName("address")
  val address: Address? = null,
  @SerialName("boundingbox")
  val boundingbox: List<String>? = null,
  @SerialName("display_name")
  val displayName: String? = null,
  @SerialName("lat")
  val lat: String? = null,
  @SerialName("licence")
  val licence: String? = null,
  @SerialName("lon")
  val lon: String? = null,
  @SerialName("osm_id")
  val osmId: Int? = null,
  @SerialName("osm_type")
  val osmType: String? = null,
  @SerialName("place_id")
  val placeId: Int? = null
)