package com.maxnovikov.filmSearch.data.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmNw(
  @SerialName("countries")
  val countries: List<Country>? = null,
  @SerialName("filmId")
  val filmId: Int? = null,
  @SerialName("filmLength")
  val filmLength: String? = null,
  @SerialName("genres")
  val genres: List<Genre>? = null,
  @SerialName("nameEn")
  val nameEn: String? = null,
  @SerialName("nameRu")
  val nameRu: String? = null,
  @SerialName("posterUrl")
  val posterUrl: String? = null,
  @SerialName("posterUrlPreview")
  val posterUrlPreview: String? = null,
  @SerialName("rating")
  val rating: String? = null,
  @SerialName("ratingVoteCount")
  val ratingVoteCount: Int? = null,
  @SerialName("year")
  val year: String? = null
)