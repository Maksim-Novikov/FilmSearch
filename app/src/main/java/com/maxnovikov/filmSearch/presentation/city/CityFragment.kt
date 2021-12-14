package com.maxnovikov.filmSearch.presentation.city

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.maxnovikov.filmSearch.presentation.common.BaseFragment
import com.maxnovikov.filmsearch.R
import com.maxnovikov.filmsearch.databinding.CityScreenBinding

class CityFragment : BaseFragment(R.layout.city_screen) {

  private val viewBinding by viewBinding(CityScreenBinding::bind)
  private var wasRationalDialogShown = false

  private val permissionRequest = registerForActivityResult(
    ActivityResultContracts.RequestMultiplePermissions()
  ) { permissions ->

    when {
      permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true -> {
        requestLocation(true)
      }
      permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true -> {
        requestLocation(false)
      }
      !wasRationalDialogShown && !shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {
        showSettings()
      }
    }

  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewBinding.cityRequestGeo.setOnClickListener {
      getCity()
    }
  }

  private fun getCity() {

    when {
      ContextCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.ACCESS_FINE_LOCATION
      ) == PackageManager.PERMISSION_GRANTED -> {
        requestLocation(true)
      }
      ContextCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.ACCESS_COARSE_LOCATION
      ) == PackageManager.PERMISSION_GRANTED -> {
        requestLocation(false)
      }
      shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {
        showRationalDialog()
      }
      shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION) -> {
        showRationalDialog()
      }
      else -> {
        requestPermission(false)
      }
    }
  }

  private fun showSettings() {
    AlertDialog.Builder(requireContext())
      .setTitle("Настройки")
      .setMessage("Для определения города необходимо дать разрешение на местоположение в настройках")
      .setPositiveButton("Ок") { _, _ ->
        openSettings()
      }
      .setNegativeButton("Отмена") { _, _ -> }
      .create()
      .show()
  }

  private fun openSettings() {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    intent.data = Uri.parse("package:${requireContext().packageName}")
    startActivity(intent)
  }

  private fun showRationalDialog() {
    AlertDialog.Builder(requireContext())
      .setTitle("Объяснение")
      .setMessage("Для определения города необходимо дать разрешение на местоположение")
      .setPositiveButton("Ок") { _, _ ->
        requestPermission(true)
      }
      .setNegativeButton("Отмена") { _, _ -> }
      .create()
      .show()
  }

  private fun requestPermission(wasRationalDialogShown: Boolean) {
    this.wasRationalDialogShown = wasRationalDialogShown
    permissionRequest.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION))
  }

  private fun requestLocation(isFineLocation: Boolean) {
    Toast.makeText(
      requireContext(),
      "requestLocation isFineLocation = $isFineLocation",
      Toast.LENGTH_SHORT
    ).show()
  }

}