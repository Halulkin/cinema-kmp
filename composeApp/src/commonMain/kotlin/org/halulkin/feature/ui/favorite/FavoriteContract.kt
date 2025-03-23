package org.halulkin.feature.ui.favorite

import org.halulkin.feature.domain.model.Movie

data class FavoriteState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
)

data class FavoriteActions(
    val onMovieClick: (Int) -> Unit = {},
)
