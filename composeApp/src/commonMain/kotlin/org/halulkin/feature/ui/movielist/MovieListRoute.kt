package org.halulkin.feature.ui.movielist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MovieListRoute(
    viewModel: MovieListViewModel = koinViewModel<MovieListViewModel>(),
) {
    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()
    val actions = rememberMovieListActions(viewModel)

    MovieListScreen(uiState, actions)
}

@Composable
fun rememberMovieListActions(
    viewModel: MovieListViewModel
) = remember(viewModel) {
    MovieListActions()
}
