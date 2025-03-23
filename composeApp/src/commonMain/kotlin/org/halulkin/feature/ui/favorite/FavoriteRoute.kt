package org.halulkin.feature.ui.favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FavoriteRoute(
    onMovieClick: (Int) -> Unit,
    viewModel: FavoriteViewModel = koinViewModel<FavoriteViewModel>()
) {
    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()

    val actions = rememberFavoriteActions(
        onMovieClick = onMovieClick,
        viewModel = viewModel
    )

    FavoriteScreen(uiState, actions)
}

@Composable
fun rememberFavoriteActions(
    onMovieClick: (Int) -> Unit,
    viewModel: FavoriteViewModel
) = remember(viewModel) {
    FavoriteActions(
        onMovieClick = onMovieClick
    )
}

