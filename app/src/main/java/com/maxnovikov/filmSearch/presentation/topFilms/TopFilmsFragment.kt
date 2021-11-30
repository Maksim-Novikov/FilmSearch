package com.maxnovikov.filmSearch.presentation.topFilms

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.maxnovikov.filmSearch.App
import com.maxnovikov.filmSearch.domain.entity.Film
import com.maxnovikov.filmSearch.presentation.common.BaseFragment
import com.maxnovikov.filmSearch.presentation.filmDetail.FilmDetailFragment
import com.maxnovikov.filmSearch.presentation.filmDetail.FilmDetailFragment.Companion.FILM_DETAIL_RATING_KEY
import com.maxnovikov.filmSearch.presentation.filmDetail.FilmDetailFragment.Companion.FILM_DETAIL_RESULT_KEY
import com.maxnovikov.filmsearch.R
import com.maxnovikov.filmsearch.databinding.TopFilmsScreenBinding
import javax.inject.Inject
import javax.inject.Provider

class TopFilmsFragment : BaseFragment(R.layout.top_films_screen) {

  @Inject
  lateinit var topFilmsViewModel: Provider<TopFilmsViewModel>

  private val viewBinding by viewBinding(TopFilmsScreenBinding::bind)
  private val viewModel by viewModels<TopFilmsViewModel> {
    object : ViewModelProvider.Factory {
      override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        topFilmsViewModel.get() as T
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    App.component.inject(this)
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

    val topFilmsAdapter = TopFilmsAdapter(viewModel::onFilmClicked)
    with(viewBinding.topFilmList) {
      adapter = topFilmsAdapter
      layoutManager = LinearLayoutManager(context)
    }
    viewModel.filmState.observe(viewLifecycleOwner) {
      topFilmsAdapter.submitList(it)
    }
    viewModel.openDetailAction.observe(viewLifecycleOwner) {
      openDetail(it)
    }
  }

  private fun openDetail(film: Film) {
    parentFragmentManager.commit(allowStateLoss = true) {
      replace(R.id.main_activity_container, FilmDetailFragment.newInstance(film))
      addToBackStack(null)
    }
  }
}

