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

    private const val FAVORITES_KEY = "FAVORITES_KEY"
  }

  private var films: List<Film>
    get() {
      return sharedPreferences.getString(FAVORITES_KEY, null)?.let {
        Json.decodeFromString(it)
      } ?: emptyList()
    }
    set(value) {
      state.value = value
      sharedPreferences.edit {
        putString(FAVORITES_KEY, Json.encodeToString(value))
      }
    }

  private val state = MutableStateFlow(films)

  override suspend fun add(film: Film) {
    films = films + film
  }

  override suspend fun delete(film: Film) {
    films = films - film
  }

  override suspend fun isInFavorites(film: Film): Boolean {
    return films.contains(film)
  }

  override fun getFavorites(): Flow<List<Film>> = state

  override fun getCount(): Flow<Int> = state.map { it.size }
}