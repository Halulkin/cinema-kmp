package org.halulkin.feature.ui.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun MovieCard(
    title: String,
    image: String,
    voteAverage: Double,
    imageSize: DpSize,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Column {
            AsyncImage(
                model = image,
                contentDescription = "Trending movie poster",
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .size(imageSize),
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Visible,
                maxLines = 2,
                modifier = Modifier.padding(vertical = 8.dp),
            )
        }
        Rating(
            voteAverage = voteAverage,
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.TopStart),
        )
    }
}
