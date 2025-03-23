package org.halulkin.feature.ui.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.halulkin.designsystem.components.LottieAnimation
import org.halulkin.feature.ui.home.components.MovieCard
import org.halulkin.feature.ui.home.components.MovieCardStyle

@Composable
fun FavoriteScreen(
    state: FavoriteState,
    actions: FavoriteActions,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            LottieAnimation(filePath = "files/bear.json")
        } else {
            FavoriteScreenContent(
                state = state,
                onMovieClick = actions.onMovieClick,
            )
        }
    }
}

@Composable
private fun FavoriteScreenContent(
    state: FavoriteState,
    onMovieClick: (Int) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxSize(),
    ) {
        items(state.movies) {
            MovieCard(
                image = it.image,
                title = it.name,
                voteAverage = it.voteAverage,
                onClick = { onMovieClick(it.id) },
                imageSize = MovieCardStyle.Large.imageSize,
                modifier = Modifier
                    .size(MovieCardStyle.Large.cardSize),
            )
        }
    }
}
