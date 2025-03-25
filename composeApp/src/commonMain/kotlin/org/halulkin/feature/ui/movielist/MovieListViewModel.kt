package org.halulkin.feature.ui.movielist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.halulkin.feature.domain.model.MovieType

class MovieListViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        const val MOVIES_TYPE_KEY = "moviesType"
    }

    private val moviesType: MovieType = checkNotNull(savedStateHandle[MOVIES_TYPE_KEY])

    private val _stateFlow: MutableStateFlow<MovieListState> = MutableStateFlow(MovieListState())
    val stateFlow: StateFlow<MovieListState> = _stateFlow.asStateFlow()

    init {
        println("MovieListViewModel init: $moviesType")
    }
}
