package com.maxnovikov.filmSearch.data.network

import com.maxnovikov.filmSearch.domain.FilmRepository
import com.maxnovikov.filmSearch.domain.entity.Film
import com.maxnovikov.filmSearch.domain.entity.TopType

class FilmRepositoryImpl(
  private val filmApi: FilmApi
) : FilmRepository {

  override suspend fun getTopFilms(topType: TopType, page: Int): List<Film> {
    return filmApi.getTopFilms(type = topType.serverType, page)
      .films?.mapNotNull { filmNw ->
        Film(
          name = filmNw.nameRu ?: return@mapNotNull null,
          year = filmNw.year?.toIntOrNull() ?: return@mapNotNull null,
          posterUrl = filmNw.posterUrl ?: "",
          posterUrlPreview = filmNw.posterUrlPreview ?: "",
          rating = filmNw.rating
        )
      } ?: emptyList()
  }
}