package org.halulkin.feature.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.halulkin.feature.domain.usecase.GetFavoriteMoviesUseCase

class FavoriteViewModel(
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<FavoriteState> = MutableStateFlow(FavoriteState())
    val stateFlow: StateFlow<FavoriteState> = _stateFlow.asStateFlow()

    init {
        getFavoriteMovies()
    }

    private fun getFavoriteMovies() = viewModelScope.launch {
        _stateFlow.update { it.copy(isLoading = true) }

        getFavoriteMoviesUseCase()
            .onSuccess { favoriteMovies ->
                favoriteMovies.collectLatest { favoriteMoviesResult ->
                    _stateFlow.update {
                        it.copy(movies = favoriteMoviesResult, isLoading = false)
                    }
                }
            }.onFailure {
                println("Error: $it")
            }
    }
}
