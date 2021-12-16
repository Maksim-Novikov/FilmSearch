package com.maxnovikov.filmSearch.presentation

import android.os.Bundle
import com.maxnovikov.filmSearch.presentation.common.BaseActivity
import com.maxnovikov.filmSearch.presentation.common.navigate
import com.maxnovikov.filmSearch.presentation.topFilms.TopFilmsFragment
import com.maxnovikov.filmsearch.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity)

    if (savedInstanceState == null) {
      supportFragmentManager.navigate(TopFilmsFragment())
    }
  }
}