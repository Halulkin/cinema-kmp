package org.halulkin.feature.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.LazyPagingItems
import org.halulkin.feature.domain.model.Movie

@Composable
fun MovieSection(
    title: String,
    movies: LazyPagingItems<Movie>?,
    style: MovieCardStyle,
    onMovieClick: (Int) -> Unit,
    onSeeAllClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
            )
            TextButton(
                onClick = onSeeAllClick,
                contentPadding = PaddingValues(),
            ) {
                Text(
                    text = "See all",
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        ) {
            items(movies?.itemCount ?: 0) { index ->
                movies?.get(index)?.let {
                    MovieCard(
                        image = it.image,
                        title = it.name,
                        voteAverage = it.voteAverage,
                        onClick = { onMovieClick(it.id) },
                        imageSize = style.imageSize,
                        modifier = Modifier.size(style.cardSize),
                    )
                }
            }
        }
    }
}
