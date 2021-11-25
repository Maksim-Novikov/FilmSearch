package com.maxnovikov.filmSearch.presentation.topFilms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maxnovikov.filmSearch.domain.entity.Film
import com.maxnovikov.filmSearch.presentation.common.setImageUrl
import com.maxnovikov.filmSearch.presentation.topFilms.TopFilmsAdapter.ViewHolder
import com.maxnovikov.filmsearch.R
import com.maxnovikov.filmsearch.databinding.ItemFilmBinding

class TopFilmsAdapter(
  private val onFilmClicked: (Film) -> Unit
) : ListAdapter<Film, ViewHolder>(
  object : DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean =
      oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean = oldItem == newItem

  }
) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
    ViewHolder(
      ItemFilmBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
      )
    )

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    with(holder.binding) {
      val item = getItem(position)
      itemFilmName.text = item.name
      itemFilmYear.text = item.year.toString()
      itemFilmRating.text = root.context.getString(R.string.item_film_rating_pattern, item.rating)
      itemFilmPoster.setImageUrl(item.posterUrlPreview)
      root.setOnClickListener { onFilmClicked(item) }
    }
  }

  class ViewHolder(val binding: ItemFilmBinding) : RecyclerView.ViewHolder(binding.root)
}