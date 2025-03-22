package org.halulkin.feature.ui.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MovieDescription(
    description: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(text = description)
    }
}
