package org.halulkin.feature.ui.movielist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.paging.cachedIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.halulkin.feature.domain.model.MovieType
import org.halulkin.feature.domain.usecase.GetPagingMoviesUseCase

class MovieListViewModel(
    private val getPagingMoviesUseCase: GetPagingMoviesUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        const val MOVIES_TYPE_KEY = "moviesType"
    }

    private val moviesType: MovieType = checkNotNull(
        savedStateHandle.get<String>(MOVIES_TYPE_KEY)?.let {
            MovieType.valueOf(it)
        }
    )

    private val _stateFlow: MutableStateFlow<MovieListState> = MutableStateFlow(
        MovieListState(headerTitle = moviesType.name)
    )
    val stateFlow: StateFlow<MovieListState> = _stateFlow.asStateFlow()

    init {
        loadMovies(moviesType)
    }

    private fun loadMovies(movieType: MovieType) = viewModelScope.launch {
        getPagingMoviesUseCase(movieType)
            .onSuccess { movies ->
                _stateFlow.update {
                    it.copy(movies = movies.cachedIn(viewModelScope))
                }
            }.onFailure { error ->
                println("Error: $error")
            }
    }
}
