package org.halulkin.feature.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.halulkin.feature.domain.usecase.GetMovieUseCase
import org.halulkin.feature.domain.usecase.ToggleFavoriteMovieUseCase

class MovieDetailsViewModel(
    private val getMovieUseCase: GetMovieUseCase,
    private val toggleFavoriteMovieUseCase: ToggleFavoriteMovieUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    companion object {
        const val MOVIE_ID_KEY = "movieId"
    }

    private val movieId: Int = checkNotNull(savedStateHandle[MOVIE_ID_KEY])

    private val _stateFlow = MutableStateFlow<MovieDetailsState>(MovieDetailsState())
    val stateFlow = _stateFlow.asStateFlow()

    init {
        getMovie(movieId)
    }

    private fun getMovie(id: Int) = viewModelScope.launch {
        _stateFlow.update { it.copy(isLoading = true) }

        getMovieUseCase(id)
            .onSuccess { movie ->
                _stateFlow.update { it.copy(isLoading = false, movie = movie) }
            }
            .onFailure { error ->
                _stateFlow.update { it.copy(isLoading = false, error = error) }
            }
    }

    fun toggleFavoriteMovie() = viewModelScope.launch {
        val currentMovie = stateFlow.value.movie
        toggleFavoriteMovieUseCase(currentMovie).onSuccess {
            _stateFlow.update {
                it.copy(movie = currentMovie.copy(isFavorite = !currentMovie.isFavorite))
            }
        }
    }
}
