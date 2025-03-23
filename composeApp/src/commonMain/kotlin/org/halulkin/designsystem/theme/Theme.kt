package org.halulkin.designsystem.theme

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.request.crossfade
import coil3.util.DebugLogger

private val DarkColorScheme = darkColorScheme(
    primary = Purple01,
    onPrimary = TextPrimaryOnDark,
    secondary = Purple02,
    onSecondary = TextSecondaryOnDark,
    tertiary = Purple03,
    onTertiary = TextPrimaryOnLight,
    background = Purple06,
    onBackground = TextPrimaryOnDark,
    surface = Purple05,
    surfaceContainer = Purple05,
    onSurface = IconPrimaryOnDark,
    onSurfaceVariant = IconSecondaryOnDark,
    secondaryContainer = Purple04,
    onSecondaryContainer = IconPrimaryOnDark,
    error = SystemError,
    onError = TextPrimaryOnDark,
)

@Composable
fun CinemaTheme(content: @Composable () -> Unit) {
    SingletonImageLoader.setSafe { context ->
        ImageLoader.Builder(context)
            .crossfade(true)
            .logger(DebugLogger())
            .build()
    }

    CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onPrimary) {
        MaterialTheme(
            colorScheme = DarkColorScheme,
            typography = MaterialTheme.typography,
            content = content,
        )
    }
}
