package org.halulkin.feature.ui.movielist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MovieListRoute(
    viewModel: MovieListViewModel = koinViewModel<MovieListViewModel>(),
    onMovieClick: (Int) -> Unit
) {
    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()
    val actions = rememberMovieListActions(onMovieClick, viewModel)

    MovieListScreen(uiState, actions)
}

@Composable
fun rememberMovieListActions(
    onMovieClick: (Int) -> Unit,
    viewModel: MovieListViewModel
) = remember(viewModel) {
    MovieListActions(
        onMovieClicked = onMovieClick
    )
}
