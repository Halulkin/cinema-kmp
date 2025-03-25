package org.halulkin.feature.ui.home

import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.halulkin.feature.domain.model.Movie
import org.halulkin.feature.domain.model.MovieType

data class HomeState(
    val isLoading: Boolean = true,
    val error: Throwable? = null,
    val movies: Map<MovieType, Flow<PagingData<Movie>>> = emptyMap(),
)

data class HomeActions(
    val onMovieClick: (Int) -> Unit = {},
    val onMovieListClick: (MovieType) -> Unit = {},
)
