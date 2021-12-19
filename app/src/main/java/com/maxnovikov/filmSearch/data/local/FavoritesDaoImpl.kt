package com.maxnovikov.filmSearch.data.local

import android.content.SharedPreferences
import androidx.core.content.edit
import com.maxnovikov.filmSearch.domain.entity.Film
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class FavoritesDaoImpl @Inject constructor(
  private val sharedPreferences: SharedPreferences
) : FavoritesDao {

  companion object {

    private const val FILMS_KEY = "FILMS_KEY"
  }

  private var films: List<Film>
    get() {
      return sharedPreferences.getString(FILMS_KEY, null)?.let {
        Json.decodeFromString(it)
      } ?: emptyList()
    }
    set(value) {
      filmsState.value = value
      sharedPreferences.edit {
        putString(FILMS_KEY, Json.encodeToString(value))
      }
    }

  private val filmsState = MutableStateFlow(films)

  override suspend fun addToFavorites(film: Film) {
    films = (films + film).distinctBy { it.name }
  }

  override suspend fun removeFromFavorites(film: Film) {
    films = films - film
  }

  override suspend fun isInFavorites(film: Film): Boolean {
    return films.contains(film)
  }

  override fun favoritesCount(): Flow<Int> = filmsState.map { it.size }
}