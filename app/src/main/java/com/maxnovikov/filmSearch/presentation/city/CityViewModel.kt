package com.maxnovikov.filmSearch.presentation.city

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxnovikov.filmSearch.domain.GeoRepository
import com.maxnovikov.filmSearch.domain.entity.City
import com.maxnovikov.filmSearch.presentation.common.launchWithErrorHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(
  private val geoRepository: GeoRepository
) : ViewModel() {

  private val _cityState = MutableLiveData<City>()
  val cityState: LiveData<City> = _cityState

  fun onLocationResult(latitude: Double, longitude: Double) {
    viewModelScope.launchWithErrorHandler {
      val city = geoRepository.getCity(latitude, longitude)
      _cityState.value = city
    }
  }

}