package com.maxnovikov.filmSearch.data.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopFilmsResponse(
  @SerialName("films")
  val films: List<FilmNw>?,
  @SerialName("pagesCount")
  val pagesCount: Int?
)