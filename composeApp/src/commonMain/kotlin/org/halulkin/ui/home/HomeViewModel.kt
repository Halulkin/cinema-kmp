package org.halulkin.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.halulkin.domain.model.MovieType

class HomeViewModel() : ViewModel() {

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

    }
}
