package org.halulkin.feature

import androidx.compose.runtime.Composable
import org.halulkin.designsystem.theme.CinemaTheme
import org.halulkin.feature.ui.navigation.MainScreen

@Composable
fun ComposeApp() {
    CinemaTheme {
        MainScreen()
    }
}
