package com.maxnovikov.filmSearch.presentation.filmDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxnovikov.filmSearch.data.local.FavoritesDao
import com.maxnovikov.filmSearch.domain.FilmRepository
import com.maxnovikov.filmSearch.domain.entity.Film
import com.maxnovikov.filmSearch.presentation.common.SingleLiveEvent
import com.maxnovikov.filmSearch.presentation.common.launchWithErrorHandler
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class FilmDetailViewModel @AssistedInject constructor(
  @Assisted private val film: Film,
  private val filmRepository: FilmRepository,
  private val favoritesDao: FavoritesDao
) : ViewModel() {

  @AssistedFactory
  interface Factory {

    fun create(film: Film): FilmDetailViewModel
  }

  private val _filmState = MutableLiveData(film)
  val filmState: LiveData<Film> = _filmState

  private val _backAction = SingleLiveEvent<Unit>()
  val backAction: LiveData<Unit> = _backAction

  private val _favoritesState = MutableLiveData<Boolean>()
  val favoritesState: LiveData<Boolean> = _favoritesState

  init {
    viewModelScope.launchWithErrorHandler {
      _favoritesState.value = favoritesDao.isInFavorites(film)
    }
  }

  fun onBackPressed() {
    _backAction.value = Unit
  }

  fun onFavoritesClick() {
    val isInFavorites = _favoritesState.value ?: false
    _favoritesState.value = !isInFavorites
    viewModelScope.launchWithErrorHandler {
      if (isInFavorites) {
        favoritesDao.removeFromFavorites(film)
      } else {
        favoritesDao.addToFavorites(film)
      }
    }
  }
}