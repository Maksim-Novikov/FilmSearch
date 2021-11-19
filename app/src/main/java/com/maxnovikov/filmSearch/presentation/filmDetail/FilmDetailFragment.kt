package com.maxnovikov.filmSearch.presentation.filmDetail

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.maxnovikov.filmSearch.domain.entity.Film
import com.maxnovikov.filmSearch.presentation.common.BaseFragment
import com.maxnovikov.filmsearch.R
import com.maxnovikov.filmsearch.databinding.FilmDetailScreenBinding

class FilmDetailFragment : BaseFragment(R.layout.film_detail_screen) {

  companion object {

    fun newInstance(film: Film) = FilmDetailFragment().apply {
      arguments = bundleOf(FILM_DETAIL_DATA_KEY to film)
    }

    private const val FILM_DETAIL_DATA_KEY = "FILM_DETAIL_DATA_KEY"
    const val FILM_DETAIL_RESULT_KEY = "FILM_DETAIL_RESULT_KEY"
    const val FILM_DETAIL_RATING_KEY = "FILM_DETAIL_RATING_KEY"
  }

  private val viewBiding by viewBinding(FilmDetailScreenBinding::bind)
  private val viewModel by viewModels<FilmDetailViewModel> {
    object : ViewModelProvider.Factory {
      override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        FilmDetailViewModel(arguments?.getParcelable(FILM_DETAIL_DATA_KEY)!!) as T
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel.filmState.observe(viewLifecycleOwner) { film ->
      viewBiding.filmDetailYear.text = film.year.toString()
      viewBiding.filmDetailName.text = film.name
    }
    viewModel.backAction.observe(viewLifecycleOwner) {
      closeScreen()
    }
    viewBiding.filmDetailBack.setOnClickListener {
      viewModel.onBackPressed()
    }
  }

  private fun closeScreen() {
    parentFragmentManager.popBackStack()
    setFragmentResult(FILM_DETAIL_RESULT_KEY, bundleOf(FILM_DETAIL_RATING_KEY to 5))
  }

}

