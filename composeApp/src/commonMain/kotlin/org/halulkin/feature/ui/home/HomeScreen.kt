package org.halulkin.feature.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paging.LoadStateError
import app.cash.paging.LoadStateLoading
import app.cash.paging.compose.collectAsLazyPagingItems
import kotlinx.io.IOException
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
    val trendingMovies = state.movies[MovieType.Trending]?.collectAsLazyPagingItems()
    val popularMovies = state.movies[MovieType.Popular]?.collectAsLazyPagingItems()
    val topRatedMovies = state.movies[MovieType.TopRated]?.collectAsLazyPagingItems()

    val trendingRefresh = trendingMovies?.loadState?.refresh
    val popularRefresh = popularMovies?.loadState?.refresh
    val topRatedRefresh = topRatedMovies?.loadState?.refresh

    val trendingLoading = trendingRefresh is LoadStateLoading
    val popularLoading = popularRefresh is LoadStateLoading
    val topRatedLoading = topRatedRefresh is LoadStateLoading
    val anyLoading = trendingLoading || popularLoading || topRatedLoading || state.isLoading

    val trendingError = trendingRefresh is LoadStateError && trendingRefresh.error is IOException
    val popularError = popularRefresh is LoadStateError && popularRefresh.error is IOException
    val topRatedError = topRatedRefresh is LoadStateError && topRatedRefresh.error is IOException
    val allError = trendingError && popularError && topRatedError

    Box(Modifier.fillMaxSize(), Alignment.Center) {
        when {
            anyLoading -> {
                LoadingContent()
            }
            allError -> {
                ErrorContent(
                    onClick = {
                        trendingMovies.retry()
                        popularMovies.retry()
                        topRatedMovies.retry()
                    }
                )
            }

            else -> {
                Column(
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    MovieSection(
                        title = "Trending",
                        movies = trendingMovies,
                        style = MovieCardStyle.Small,
                        onMovieClick = actions.onMovieClick,
                    )
                    MovieSection(
                        title = "Popular",
                        movies = popularMovies,
                        style = MovieCardStyle.Small,
                        onMovieClick = actions.onMovieClick,
                    )
                    MovieSection(
                        title = "Top Rated",
                        movies = topRatedMovies,
                        style = MovieCardStyle.Small,
                        onMovieClick = actions.onMovieClick,
                    )
                }
            }
        }
    }
}