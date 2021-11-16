package com.maxnovikov.filmSearch.presentation.topFilms

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.maxnovikov.filmSearch.presentation.common.BaseFragment
import com.maxnovikov.filmsearch.R
import com.maxnovikov.filmsearch.databinding.TopFilmsScreenBinding

class TopFilmsFragment : BaseFragment(R.layout.top_films_screen) {

  private val viewBinding by viewBinding(TopFilmsScreenBinding::bind)

  private var count: Int = 0

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewBinding.topFilmAddCount.setOnClickListener {
      count++
      setTextToCounter()
    }
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    outState.putInt(COUNTER_SAVE_KEY, count)
  }

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    super.onViewStateRestored(savedInstanceState)
    savedInstanceState?.getInt(COUNTER_SAVE_KEY)?.let {
      count = it
      setTextToCounter()
    }
  }

  private fun setTextToCounter() {
    viewBinding.topFilmCount.text = count.toString()
  }

  companion object {

    private const val COUNTER_SAVE_KEY = "COUNTER_SAVE_KEY"
  }
}