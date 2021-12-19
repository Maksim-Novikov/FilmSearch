package com.maxnovikov.filmSearch.data.local

import com.maxnovikov.filmSearch.domain.entity.Film
import kotlinx.coroutines.flow.Flow

interface FavoritesDao {

  suspend fun addToFavorites(film: Film)

  suspend fun removeFromFavorites(film: Film)

  suspend fun isInFavorites(film: Film): Boolean

  fun favoritesCount(): Flow<Int>
}