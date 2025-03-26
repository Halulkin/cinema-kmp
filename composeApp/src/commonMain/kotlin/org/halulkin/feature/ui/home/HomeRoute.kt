package org.halulkin.feature.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.halulkin.feature.domain.model.MovieType
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeRoute(
    onMovieClick: (Int) -> Unit,
    onMovieListClick: (MovieType) -> Unit,
    viewModel: HomeViewModel = koinViewModel<HomeViewModel>()
) {
    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()

    val actions = rememberHomeActions(
        viewModel = viewModel,
        onMovieClick = onMovieClick,
        onMovieListClick = onMovieListClick
    )

    HomeScreen(uiState, actions)
}

@Composable
fun rememberHomeActions(
    viewModel: HomeViewModel,
    onMovieClick: (Int) -> Unit,
    onMovieListClick: (MovieType) -> Unit
) = remember(viewModel) {
    HomeActions(
        onMovieClick = onMovieClick,
        onMovieListClick = onMovieListClick,
        onRetry = viewModel::retry,
    )
}
