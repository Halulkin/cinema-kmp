package org.halulkin.feature.ui.movielist

import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import org.halulkin.feature.domain.model.Movie

data class MovieListState(
    val headerTitle: String = "",
    val movies: Flow<PagingData<Movie>> = emptyFlow()
)

data class MovieListActions(
    val onMovieClicked: (Int) -> Unit = {},
    val onBackClick: () -> Unit = {},
)
