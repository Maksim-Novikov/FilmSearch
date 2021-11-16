package com.maxnovikov.filmSearch.presentation

import android.os.Bundle
import com.maxnovikov.filmSearch.presentation.common.BaseActivity
import com.maxnovikov.filmSearch.presentation.topFilms.TopFilmsFragment
import com.maxnovikov.filmsearch.R

class MainActivity : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity)

    if (savedInstanceState == null) {
      val fragment = TopFilmsFragment()
      supportFragmentManager.beginTransaction()
        .replace(R.id.main_activity_container, fragment)
        .commitAllowingStateLoss()
    }
  }
}