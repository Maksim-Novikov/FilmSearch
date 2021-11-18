package com.maxnovikov.filmSearch.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
  val name: String,
  val year: Int
) : Parcelable