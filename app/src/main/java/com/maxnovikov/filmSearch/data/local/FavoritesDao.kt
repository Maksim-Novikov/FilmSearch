package com.maxnovikov.filmSearch.data.local

import com.maxnovikov.filmSearch.domain.entity.Film
import kotlinx.coroutines.flow.Flow

interface FavoritesDao {

  suspend fun add(film: Film)

  suspend fun delete(film: Film)

  suspend fun isInFavorites(film: Film): Boolean

  fun getFavorites(): Flow<List<Film>>

  fun getCount(): Flow<Int>
}