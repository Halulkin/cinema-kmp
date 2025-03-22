package org.halulkin.ui.home

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.halulkin.domain.model.Movie
import org.halulkin.domain.model.MovieType

data class HomeState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val movies: Map<MovieType, Flow<PagingData<Movie>>> = emptyMap(),
)

data class HomeActions(
    val onRefresh: () -> Unit = {},
)
