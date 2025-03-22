package org.halulkin.feature.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.halulkin.feature.ui.details.components.MovieDescription
import org.halulkin.feature.ui.details.components.MovieDetailsHeader
import org.halulkin.feature.ui.details.components.MovieTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(
    state: MovieDetailsState,
    actions: MovieDetailsActions,
) {
    PullToRefreshBox(
        isRefreshing = state.isLoading,
        onRefresh = actions.onBackClick,
    ) {
        if (state.error != null) {
            // Show Error
        } else {
            MovieDetailsContent(
                state = state,
                actions = actions,
            )
        }
    }
}

@Composable
fun MovieDetailsContent(
    state: MovieDetailsState,
    actions: MovieDetailsActions,
) {
    Column(
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize(),
    ) {
        MovieDetailsHeader(
            isFavorite = state.movie.isFavorite,
            image = state.movie.image,
            onBackClick = actions.onBackClick,
            onFavoriteClick = actions.onFavoriteClick,
        )
        MovieTitle(
            title = state.movie.name,
            voteAverage = state.movie.voteAverage,
            voteCount = state.movie.voteCount,
        )
        MovieDescription(
            description = state.movie.overview,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
        )
    }
}
