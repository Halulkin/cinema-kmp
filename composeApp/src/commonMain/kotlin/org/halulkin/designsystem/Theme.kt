package org.halulkin.designsystem

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

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
    CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onPrimary) {
        MaterialTheme(
            colorScheme = DarkColorScheme,
            content = content,
            typography = MaterialTheme.typography,
        )
    }
}