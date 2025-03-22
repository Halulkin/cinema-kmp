package org.halulkin.feature.ui.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MovieDetailsRoute(
    onBackPress: () -> Unit,
    viewModel: MovieDetailsViewModel = koinViewModel<MovieDetailsViewModel>(),
) {
    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()

    val actions = rememberMovieDetailsActions(
        viewModel = viewModel,
        onBackPress = onBackPress,
    )

    MovieDetailsScreen(uiState, actions)
}

@Composable
fun rememberMovieDetailsActions(
    viewModel: MovieDetailsViewModel,
    onBackPress: () -> Unit,
) = remember(viewModel) {
    MovieDetailsActions(
        onBackClick = onBackPress,
        onFavoriteClick = viewModel::toggleFavoriteMovie,
    )
}
