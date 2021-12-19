package com.maxnovikov.filmSearch.presentation.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.maxnovikov.filmSearch.presentation.TabProvider
import com.maxnovikov.filmsearch.R
import com.maxnovikov.filmsearch.R.anim

fun Fragment.navigate(fragment: Fragment) {
  parentFragmentManager.commit(allowStateLoss = true) {
    setReorderingAllowed(true)
    setCustomAnimations(
      anim.slide_from_right,
      anim.slide_to_left,
      anim.slide_from_left,
      anim.slide_to_right
    )
    replace(R.id.main_activity_container, fragment)
    addToBackStack((activity as? TabProvider)?.currentTab?.name)
  }
}