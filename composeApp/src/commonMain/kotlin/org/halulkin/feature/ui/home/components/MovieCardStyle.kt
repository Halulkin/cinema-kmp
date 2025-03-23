package org.halulkin.feature.ui.home.components

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

enum class MovieCardStyle(
    val cardSize: DpSize,
    val imageSize: DpSize,
) {
    Large(
        cardSize = DpSize(500.dp, 350.dp),
        imageSize = DpSize(500.dp, 300.dp)
    ),
    Small(
        cardSize = DpSize(140.dp, 266.dp),
        imageSize = DpSize(140.dp, 210.dp)
    ),
}
