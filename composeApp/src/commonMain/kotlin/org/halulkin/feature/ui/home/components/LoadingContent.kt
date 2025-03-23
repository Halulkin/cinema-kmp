package org.halulkin.feature.ui.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.halulkin.designsystem.components.LottieMessageContent

@Composable
fun LoadingContent(
    message: String = "Just a moment",
    modifier: Modifier = Modifier
) {
    LottieMessageContent(
        filePath = "files/loading.json",
        message = message,
        modifier = modifier
    )
}
