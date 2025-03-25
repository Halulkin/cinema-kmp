package org.halulkin.feature.ui.movielist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import org.halulkin.feature.domain.model.Movie
import org.halulkin.feature.ui.home.components.MovieCard
import org.halulkin.feature.ui.home.components.MovieCardStyle

@Composable
fun MovieListScreen(
    state: MovieListState,
    actions: MovieListActions
) {
    val movies = state.movies.collectAsLazyPagingItems()

    MovieListContent(
        movies = movies,
        actions = actions,
    )
}

@Composable
fun MovieListContent(
    movies: LazyPagingItems<Movie>,
    actions: MovieListActions
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxSize(),
    ) {
        items(movies.itemCount) { index ->
            movies[index]?.let {
                MovieCard(
                    image = it.image,
                    title = it.name,
                    voteAverage = it.voteAverage,
                    onClick = { actions.onMovieClicked(it.id) },
                    imageSize = MovieCardStyle.Large.imageSize,
                    modifier = Modifier
                        .size(MovieCardStyle.Large.cardSize),
                )
            }
        }
    }
}