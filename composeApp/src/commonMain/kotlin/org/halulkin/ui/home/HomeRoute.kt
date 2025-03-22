package org.halulkin.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = koinViewModel<HomeViewModel>()
) {
    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()

    val actions = rememberHomeActions(viewModel)

    HomeScreen(uiState, actions)
}

@Composable
fun rememberHomeActions(
    viewModel: HomeViewModel,
) = remember(viewModel) {
    HomeActions(
    )
}
