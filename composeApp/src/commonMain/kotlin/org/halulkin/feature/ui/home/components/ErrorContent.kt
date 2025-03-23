package org.halulkin.feature.ui.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.halulkin.designsystem.components.LottieMessageContent

@Composable
fun ErrorContent(
    message: String,
    modifier: Modifier = Modifier
) {
    LottieMessageContent(
        filePath = "files/error.json",
        message = message,
        modifier = modifier
    )
}
