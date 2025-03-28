package org.halulkin.feature.ui.movielist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import app.cash.paging.LoadStateError
import app.cash.paging.LoadStateLoading
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import org.halulkin.designsystem.components.AppTopBar
import org.halulkin.feature.domain.model.Movie
import org.halulkin.feature.ui.home.components.ErrorContent
import org.halulkin.feature.ui.home.components.LoadingContent
import org.halulkin.feature.ui.home.components.MovieCard
import org.halulkin.feature.ui.home.components.MovieCardStyle

@Composable
fun MovieListScreen(
    state: MovieListState,
    actions: MovieListActions
) {
    val movies = state.movies.collectAsLazyPagingItems()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (movies.loadState.refresh) {
            is LoadStateLoading -> LoadingContent()
            is LoadStateError -> ErrorContent(onClick = { movies.refresh() })
            else -> {
                MovieListContent(
                    header = state.headerTitle,
                    movies = movies,
                    actions = actions,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListContent(
    header: String,
    movies: LazyPagingItems<Movie>,
    actions: MovieListActions
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Column {
        AppTopBar(
            title = header,
            onBackButtonClick = actions.onBackClick,
            scrollBehavior = scrollBehavior,
            modifier = Modifier.fillMaxWidth(),
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
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
}
