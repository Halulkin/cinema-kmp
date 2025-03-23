package org.halulkin.feature.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.collectAsLazyPagingItems
import org.halulkin.feature.domain.model.MovieType
import org.halulkin.feature.ui.home.components.ErrorContent
import org.halulkin.feature.ui.home.components.LoadingContent
import org.halulkin.feature.ui.home.components.MovieCardStyle
import org.halulkin.feature.ui.home.components.MovieSection

@Composable
internal fun HomeScreen(
    state: HomeState,
    actions: HomeActions,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            LoadingContent()
        } else if (state.error != null) {
            ErrorContent(message = state.error)
        } else {
            HomeContent(
                state = state,
                onMovieClick = actions.onMovieClick,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeContent(
    state: HomeState,
    onMovieClick: (Int) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        MovieSection(
            title = "Trending",
            movies = state.movies[MovieType.Trending]?.collectAsLazyPagingItems(),
            style = MovieCardStyle.Small,
            onMovieClick = onMovieClick,
        )
        MovieSection(
            title = "Popular",
            movies = state.movies[MovieType.Popular]?.collectAsLazyPagingItems(),
            style = MovieCardStyle.Small,
            onMovieClick = onMovieClick,
        )
        MovieSection(
            title = "Top Rated",
            movies = state.movies[MovieType.TopRated]?.collectAsLazyPagingItems(),
            style = MovieCardStyle.Small,
            onMovieClick = onMovieClick,
        )
    }
}
