package com.maxnovikov.filmSearch.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import by.kirich1409.viewbindingdelegate.viewBinding
import com.maxnovikov.filmSearch.presentation.Tab.FAVORITES
import com.maxnovikov.filmSearch.presentation.Tab.TOP
import com.maxnovikov.filmSearch.presentation.common.BaseActivity
import com.maxnovikov.filmSearch.presentation.common.BaseFragment
import com.maxnovikov.filmSearch.presentation.favorites.FavoritesFragment
import com.maxnovikov.filmSearch.presentation.topFilms.TopFilmsFragment
import com.maxnovikov.filmsearch.R
import com.maxnovikov.filmsearch.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(), TabProvider {

  private val viewBinding by viewBinding(MainActivityBinding::bind)
  private val viewModel by viewModels<MainActivityViewModel>()
  override var currentTab: Tab? = null
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity)

    if (savedInstanceState == null) {
      onTabSelected(Tab.TOP)
    }

    viewBinding.mainActivityBottom.setOnItemSelectedListener {

      when (it.itemId) {
        R.id.action_favorites -> {
          onTabSelected(FAVORITES)
        }
        R.id.action_top -> {
          onTabSelected(TOP)
        }
      }
      true
    }
    viewModel.favoritesState.observe(this) {
      viewBinding.mainActivityBottom.getOrCreateBadge(R.id.action_favorites).number = it
    }
  }

  private val tabState = mutableMapOf<Tab, Boolean>()
  private fun onTabSelected(newTab: Tab) {

    if (currentTab == newTab) return
    val fm = supportFragmentManager
    currentTab?.let { fm.saveBackStack(it.name) }
    val currentFragment = fm.fragments.firstOrNull { it.isVisible }
    (currentFragment as? BaseFragment)?.ignoreNextAnimation()
    val wasTabInitiated = tabState[newTab] ?: false
    if (!wasTabInitiated) {
      fm.commit(allowStateLoss = true) {
        setReorderingAllowed(true)
        replace(R.id.main_activity_container, newTab.getFragment())
        addToBackStack(newTab.name)
      }
    } else {
      fm.restoreBackStack(newTab.name)
    }
    tabState[newTab] = true
    currentTab = newTab
  }

  override fun onBackPressed() {
    if (supportFragmentManager.backStackEntryCount == 1) {
      finish()
    } else {
      super.onBackPressed()
    }
  }
}

enum class Tab {
  FAVORITES, TOP
}

private fun Tab.getFragment(): Fragment {
  return when (this) {
    FAVORITES -> FavoritesFragment()
    TOP -> TopFilmsFragment()
  }
}

interface TabProvider {

  val currentTab: Tab?
}