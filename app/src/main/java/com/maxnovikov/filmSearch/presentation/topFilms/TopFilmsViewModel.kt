package com.maxnovikov.filmSearch.presentation.topFilms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxnovikov.filmSearch.domain.FilmRepository
import com.maxnovikov.filmSearch.domain.entity.Film
import com.maxnovikov.filmSearch.domain.entity.TopType.TOP_AWAIT_FILMS
import com.maxnovikov.filmSearch.presentation.common.SingleLiveEvent
import com.maxnovikov.filmSearch.presentation.common.launchWithErrorHandler
import com.maxnovikov.filmSearch.presentation.topFilms.TopFilmsState.Error
import com.maxnovikov.filmSearch.presentation.topFilms.TopFilmsState.Loading
import com.maxnovikov.filmSearch.presentation.topFilms.TopFilmsState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopFilmsViewModel @Inject constructor(
  private val filmRepository: FilmRepository
) : ViewModel() {

  private val _screenState = MutableLiveData<TopFilmsState>(Loading())
  val screenState: LiveData<TopFilmsState> = _screenState

  private val _openDetailAction = SingleLiveEvent<Film>()
  val openDetailAction: LiveData<Film> = _openDetailAction

  init {
    viewModelScope.launchWithErrorHandler(block = {
      val films: List<Film> = filmRepository.getTopFilms(TOP_AWAIT_FILMS, 1)
      _screenState.value = Success(films)
    }, onError = {
      _screenState.value = Error(it)
    })
  }

  fun onFilmClicked(film: Film) {
    _openDetailAction.value = film
  }
}

sealed class TopFilmsState {
  class Loading() : TopFilmsState()
  class Success(val films: List<Film>) : TopFilmsState()
  class Error(val throwable: Throwable) : TopFilmsState()
}