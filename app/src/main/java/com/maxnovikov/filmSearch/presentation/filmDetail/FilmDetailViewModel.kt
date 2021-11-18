package com.maxnovikov.filmSearch.presentation.filmDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maxnovikov.filmSearch.domain.entity.Film
import com.maxnovikov.filmSearch.presentation.common.SingleLiveEvent

class FilmDetailViewModel(private val film: Film) : ViewModel() {

  private val _filmState = MutableLiveData(film)
  val filmState: LiveData<Film> = _filmState

  private val _backAction = SingleLiveEvent<Unit>()
  val backAction: LiveData<Unit> = _backAction

  fun onBackPressed() {
    _backAction.value = Unit
  }
}