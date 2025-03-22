package org.halulkin.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.LazyPagingItems
import org.halulkin.domain.model.Movie

@Composable
fun MovieSection(
    title: String,
    movies: LazyPagingItems<Movie>?,
    style: MovieCardStyle,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            items(movies?.itemCount ?: 0) { index ->
                movies?.get(index)?.let {
                    MovieCard(
                        image = it.image,
                        title = it.name,
                        voteAverage = it.voteAverage,
                        onClick = { },
                        imageSize = style.imageSize,
                        modifier = Modifier
                            .size(style.cardSize),
                    )
                }
            }
        }
    }
}