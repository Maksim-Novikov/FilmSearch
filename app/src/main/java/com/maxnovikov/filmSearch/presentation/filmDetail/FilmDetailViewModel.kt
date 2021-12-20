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
import kotlinx.coroutines.launch

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
    viewModelScope.launch {
      _favoritesState.value = favoritesDao.isInFavorites(film)
    }
  }

  fun onFavoritesClicked() {
    viewModelScope.launchWithErrorHandler {
      val isInFavorites = favoritesDao.isInFavorites(film)
      _favoritesState.value = !isInFavorites
      if (isInFavorites) {
        favoritesDao.delete(film)
      } else {
        favoritesDao.add(film)
      }
    }
  }

  fun onBackPressed() {
    _backAction.value = Unit
  }
}