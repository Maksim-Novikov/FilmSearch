package com.maxnovikov.filmSearch.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxnovikov.filmSearch.domain.FilmRepository
import com.maxnovikov.filmSearch.presentation.common.launchWithErrorHandler
import com.maxnovikov.filmSearch.presentation.search.YearState.EMPTY
import com.maxnovikov.filmSearch.presentation.search.YearState.VALID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
  private val filmRepository: FilmRepository
) : ViewModel() {

  private var searchJob: Job? = null
  private val _yearFromErrorState = MutableLiveData(VALID)
  val yearFromErrorState: LiveData<YearState> = _yearFromErrorState

  private val _yearToErrorState = MutableLiveData(VALID)
  val yearToErrorState: LiveData<YearState> = _yearToErrorState

  private val _loadingState = MutableLiveData(false)
  val loadingState: LiveData<Boolean> = _loadingState

  fun search(yearFrom: String, yearTo: String) {
    var hasError = false

    if (yearFrom.isBlank()) {
      _yearFromErrorState.value = EMPTY
      hasError = true
    }
    if (yearTo.isBlank()) {
      _yearToErrorState.value = EMPTY
      hasError = true
    }

    if (hasError) return

    _yearFromErrorState.value = VALID
    _yearToErrorState.value = VALID

    _loadingState.value = true

    searchJob?.cancel()

    if (searchJob?.isActive == true) return

    searchJob = viewModelScope.launchWithErrorHandler(block = {
      filmRepository.search(yearFrom.toIntOrNull(), yearTo.toIntOrNull())
      _loadingState.value = false
    }, onError = {
      _loadingState.value = false
    })
  }

  fun onYearFromChange(yearFrom: String) {
    if (yearFrom.length == 4) {
      _yearFromErrorState.value = VALID
    }
  }

}

enum class YearState {
  EMPTY, VALID
}