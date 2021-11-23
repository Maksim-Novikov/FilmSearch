package com.maxnovikov.filmSearch.data.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Country(
  @SerialName("country")
  val country: String?
)