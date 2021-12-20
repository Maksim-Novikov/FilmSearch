package com.maxnovikov.filmSearch.presentation

import android.os.Bundle
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.maxnovikov.filmSearch.presentation.common.BaseActivity
import com.maxnovikov.filmSearch.presentation.common.navigate
import com.maxnovikov.filmSearch.presentation.topFilms.TopFilmsFragment
import com.maxnovikov.filmsearch.R
import com.maxnovikov.filmsearch.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

  private val viewModel by viewModels<MainActivityViewModel>()
  private val viewBinding by viewBinding(MainActivityBinding::bind)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity)

    if (savedInstanceState == null) {
      supportFragmentManager.navigate(TopFilmsFragment())
    }
    viewModel.favoritesCountState.observe(this) {
      viewBinding.mainActivityBottom.getOrCreateBadge(R.id.bottom_menu_favorites).number = it
    }

  }

}

