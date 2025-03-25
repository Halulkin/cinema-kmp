package org.halulkin.feature.ui.movielist

data class MovieListState(
    val movies: List<String> = emptyList()
)

data class MovieListActions(
    val onClick: () -> Unit = {}
)
