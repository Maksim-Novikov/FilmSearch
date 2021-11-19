package com.maxnovikov.filmSearch.presentation.topFilms

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.maxnovikov.filmSearch.domain.entity.Film
import com.maxnovikov.filmSearch.presentation.common.BaseFragment
import com.maxnovikov.filmSearch.presentation.filmDetail.FilmDetailFragment
import com.maxnovikov.filmSearch.presentation.filmDetail.FilmDetailFragment.Companion.FILM_DETAIL_RATING_KEY
import com.maxnovikov.filmSearch.presentation.filmDetail.FilmDetailFragment.Companion.FILM_DETAIL_RESULT_KEY
import com.maxnovikov.filmsearch.R
import com.maxnovikov.filmsearch.databinding.TopFilmsScreenBinding

class TopFilmsFragment : BaseFragment(R.layout.top_films_screen) {

  private val viewBinding by viewBinding(TopFilmsScreenBinding::bind)
  private val viewModel by viewModels<TopFilmsViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setFragmentResultListener(FILM_DETAIL_RESULT_KEY) { requestKey, bundle ->
      if (requestKey == FILM_DETAIL_RESULT_KEY) {
        val rating = bundle.getInt(FILM_DETAIL_RATING_KEY)
        showRating(rating)
      }
    }
  }

  private fun showRating(rating: Int) {
    Toast.makeText(requireContext(), "$rating", Toast.LENGTH_SHORT).show()
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewBinding.topFilmAddCount.setOnClickListener {
      viewModel.onAdd()
    }
    viewModel.countState.observe(viewLifecycleOwner) { count ->
      viewBinding.topFilmCount.text = count.toString()
    }
    viewBinding.topFilmShowDetail.setOnClickListener {
      openDetail(Film("Джентельмены", 2020))
    }
  }

  private fun openDetail(film: Film) {
    parentFragmentManager.commit(allowStateLoss = true) {
      replace(R.id.main_activity_container, FilmDetailFragment.newInstance(film))
      addToBackStack(null)
    }
  }
}

