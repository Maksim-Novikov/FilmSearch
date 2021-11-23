package com.maxnovikov.filmSearch.data.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmNw(
  @SerialName("countries")
  val countries: List<Country>?,
  @SerialName("filmId")
  val filmId: Int?,
  @SerialName("filmLength")
  val filmLength: String?,
  @SerialName("genres")
  val genres: List<Genre>?,
  @SerialName("nameEn")
  val nameEn: String?,
  @SerialName("nameRu")
  val nameRu: String?,
  @SerialName("posterUrl")
  val posterUrl: String?,
  @SerialName("posterUrlPreview")
  val posterUrlPreview: String?,
  @SerialName("rating")
  val rating: String?,
  @SerialName("ratingVoteCount")
  val ratingVoteCount: Int?,
  @SerialName("year")
  val year: String?
)