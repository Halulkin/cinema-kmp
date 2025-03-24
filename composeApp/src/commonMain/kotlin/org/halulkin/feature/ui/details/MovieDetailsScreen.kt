package org.halulkin.feature.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.halulkin.feature.ui.details.components.MovieDescription
import org.halulkin.feature.ui.details.components.MovieDetailsHeader
import org.halulkin.feature.ui.details.components.MovieTitle
import org.halulkin.feature.ui.home.components.ErrorContent
import org.halulkin.feature.ui.home.components.LoadingContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(
    state: MovieDetailsState,
    actions: MovieDetailsActions,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            LoadingContent()
        } else if (state.error != null) {
            ErrorContent()
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
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        MovieDetailsHeader(
            isFavorite = state.movie.isFavorite,
            image = state.movie.backdrop,
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
