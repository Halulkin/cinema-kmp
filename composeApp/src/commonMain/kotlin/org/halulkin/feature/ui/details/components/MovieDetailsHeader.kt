package org.halulkin.feature.ui.details.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import org.halulkin.designsystem.components.AppTopBar

@Composable
fun MovieDetailsHeader(
    isFavorite: Boolean,
    image: String,
    onBackClick: () -> Unit,
    onFavoriteClick: () -> Unit,
) {
    Box {
        AsyncImage(
            model = image,
            contentDescription = "poster",
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
        )

        AppTopBar(
            onBackButtonClick = onBackClick,
            actionButton = {
                IconButton(onClick = onFavoriteClick) {
                    Icon(
                        imageVector =
                            if (isFavorite) {
                                Icons.Filled.Favorite
                            } else {
                                Icons.Default.FavoriteBorder
                            },
                        tint = if (isFavorite) Color.Red else Color.Black,
                        contentDescription = "favorite",
                    )
                }
            },
            modifier = Modifier.align(Alignment.TopCenter),
        )
    }
}