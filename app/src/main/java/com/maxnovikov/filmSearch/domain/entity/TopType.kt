package com.maxnovikov.filmSearch.domain.entity

enum class TopType(val serverType: String) {
  TOP_250_BEST_FILMS("TOP_250_BEST_FILMS"),
  TOP_100_POPULAR_FILMS("TOP_100_POPULAR_FILMS"),
  TOP_AWAIT_FILMS("TOP_AWAIT_FILMS")
}