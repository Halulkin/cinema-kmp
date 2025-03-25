package org.halulkin.feature.ui.home

import org.halulkin.feature.domain.model.Movie
import org.halulkin.feature.domain.model.MovieType

data class HomeState(
    val isLoading: Boolean = true,
    val error: Throwable? = null,
    val movies: Map<MovieType, List<Movie>> = emptyMap(),
)

data class HomeActions(
    val onMovieClick: (Int) -> Unit = {},
    val onMovieListClick: (MovieType) -> Unit = {},
    val onRetry: () -> Unit = {},
)
