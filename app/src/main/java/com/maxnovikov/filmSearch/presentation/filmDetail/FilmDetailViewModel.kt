package com.maxnovikov.filmSearch.presentation.filmDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maxnovikov.filmSearch.domain.FilmRepository
import com.maxnovikov.filmSearch.domain.entity.Film
import com.maxnovikov.filmSearch.presentation.common.SingleLiveEvent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class FilmDetailViewModel @AssistedInject constructor(
  @Assisted private val film: Film,
  private val filmRepository: FilmRepository
) : ViewModel() {

  @AssistedFactory
  interface Factory {

    fun create(film: Film): FilmDetailViewModel
  }

  private val _filmState = MutableLiveData(film)
  val filmState: LiveData<Film> = _filmState

  private val _backAction = SingleLiveEvent<Unit>()
  val backAction: LiveData<Unit> = _backAction

  fun onBackPressed() {
    _backAction.value = Unit
  }
}