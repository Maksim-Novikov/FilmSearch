package com.maxnovikov.filmSearch.presentation.topFilms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TopFilmsViewModel : ViewModel() {

  private val _countState = MutableLiveData(0)
  val countState: LiveData<Int> = _countState

  fun onAdd() {
    _countState.value = _countState.value!! + 1
  }
}