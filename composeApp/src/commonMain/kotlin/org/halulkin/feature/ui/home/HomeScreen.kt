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
import org.halulkin.feature.domain.model.Movie
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
            ErrorContent(onClick = actions.onRetry)
        } else {
            HomeContent(
                movies = state.movies,
                onMovieClick = actions.onMovieClick,
                onSeeAllClick = actions.onMovieListClick,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeContent(
    movies: Map<MovieType, List<Movie>>,
    onMovieClick: (Int) -> Unit,
    onSeeAllClick: (MovieType) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        MovieSection(
            title = "Trending",
            movies = movies[MovieType.Trending].orEmpty(),
            style = MovieCardStyle.Small,
            onMovieClick = onMovieClick,
            onSeeAllClick = { onSeeAllClick(MovieType.Trending) },
        )
        MovieSection(
            title = "Popular",
            movies = movies[MovieType.Popular].orEmpty(),
            style = MovieCardStyle.Small,
            onMovieClick = onMovieClick,
            onSeeAllClick = { onSeeAllClick(MovieType.Popular) },
        )
        MovieSection(
            title = "Top Rated",
            movies = movies[MovieType.TopRated].orEmpty(),
            style = MovieCardStyle.Small,
            onMovieClick = onMovieClick,
            onSeeAllClick = { onSeeAllClick(MovieType.TopRated) },
        )
    }
}
