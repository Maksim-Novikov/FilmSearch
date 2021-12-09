package com.maxnovikov.filmSearch.presentation.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.maxnovikov.filmSearch.presentation.common.BaseFragment
import com.maxnovikov.filmSearch.presentation.search.YearState.EMPTY
import com.maxnovikov.filmSearch.presentation.search.YearState.VALID
import com.maxnovikov.filmsearch.R
import com.maxnovikov.filmsearch.databinding.SearchScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment(R.layout.search_screen) {

  private val viewBinding by viewBinding(SearchScreenBinding::bind)
  private val viewModel by viewModels<SearchViewModel>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    viewBinding.searchSubmit.setOnClickListener {
      search()
    }

    viewBinding.searchYearToEdit.setOnEditorActionListener { textView, actionId, keyEvent ->
      if (actionId == EditorInfo.IME_ACTION_DONE) {
        search()
      }
      true
    }

    viewModel.yearFromErrorState.observe(viewLifecycleOwner) {
      viewBinding.searchYearFromLayout.error = it.getText()
    }

    viewModel.yearToErrorState.observe(viewLifecycleOwner) {
      viewBinding.searchYearToLayout.error = it.getText()
    }

    viewModel.loadingState.observe(viewLifecycleOwner) {
      viewBinding.searchProgress.isVisible = it
    }
    viewBinding.searchYearFromEdit.addTextChangedListener {
      viewModel.onYearFromChange(it.toString())
    }
  }

  private fun search() {
    viewModel.search(
      viewBinding.searchYearFromEdit.text.toString(),
      viewBinding.searchYearToEdit.text.toString(),
    )
  }

  fun YearState.getText(): String =
    when (this) {
      EMPTY -> "Необходимо заполнить поле"
      VALID -> ""
    }
}

