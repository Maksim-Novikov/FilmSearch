package com.maxnovikov.filmSearch.domain

import com.maxnovikov.filmSearch.domain.entity.Film
import com.maxnovikov.filmSearch.domain.entity.TopType

interface FilmRepository {

  /**
   * Запрашивает список топов фильмов по типу [topType]
   * */
  suspend fun getTopFilms(topType: TopType, page: Int): List<Film>
}