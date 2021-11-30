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
import javax.inject.Inject

class TopFilmsViewModel @Inject constructor(
  private val filmRepository: FilmRepository
) : ViewModel() {

  private val _filmsState = MutableLiveData<List<Film>>()
  val filmState: LiveData<List<Film>> = _filmsState

  private val _openDetailAction = SingleLiveEvent<Film>()
  val openDetailAction: LiveData<Film> = _openDetailAction

  init {
    viewModelScope.launchWithErrorHandler {
      val films: List<Film> = filmRepository.getTopFilms(TOP_AWAIT_FILMS, 1)
      _filmsState.value = films
    }
  }

  fun onFilmClicked(film: Film) {
    _openDetailAction.value = film
  }
}