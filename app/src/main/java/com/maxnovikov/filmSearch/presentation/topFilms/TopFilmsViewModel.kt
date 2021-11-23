package com.maxnovikov.filmSearch.presentation.topFilms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxnovikov.filmSearch.domain.FilmRepository
import com.maxnovikov.filmSearch.domain.entity.Film
import com.maxnovikov.filmSearch.domain.entity.TopType.TOP_AWAIT_FILMS
import com.maxnovikov.filmSearch.presentation.common.launchWithErrorHandler

class TopFilmsViewModel(
  private val filmRepository: FilmRepository
) : ViewModel() {

  private val _countState = MutableLiveData(0)
  val countState: LiveData<Int> = _countState

  private val _filmsState = MutableLiveData<List<Film>>()
  val filmState: LiveData<List<Film>> = _filmsState

  init {
    viewModelScope.launchWithErrorHandler {
      val films: List<Film> = filmRepository.getTopFilms(TOP_AWAIT_FILMS, 1)
      _filmsState.value = films
    }
  }

  fun onAdd() {
    _countState.value = _countState.value!! + 1
  }
}