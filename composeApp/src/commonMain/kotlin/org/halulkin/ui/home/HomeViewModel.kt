package org.halulkin.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.halulkin.domain.model.MovieType
import org.halulkin.domain.usecase.GetMoviesUseCase

class HomeViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val stateFlow: StateFlow<HomeState> = _stateFlow.asStateFlow()

    init {
        loadContent()
    }

    fun onRefresh() {
        viewModelScope.coroutineContext.cancelChildren()
        loadContent()
    }

    private fun loadContent() {
        val movieMediaTypes = listOf(
            MovieType.TopRated,
            MovieType.Popular,
            MovieType.Trending,
        )
        movieMediaTypes.forEach(::loadMovies)
    }

    private fun loadMovies(
        movieType: MovieType,
    ) = viewModelScope.launch {
        _stateFlow.update { it.copy(isLoading = true) }

        getMoviesUseCase(movieType)
            .onSuccess { movies ->
                _stateFlow.update {
                    it.copy(
                        isLoading = false,
                        movies = it.movies + (movieType to movies.cachedIn(viewModelScope)),
                    )
                }
            }.onFailure { error ->
                _stateFlow.update { it.copy(isLoading = false, error = error.message) }
            }
    }
}
