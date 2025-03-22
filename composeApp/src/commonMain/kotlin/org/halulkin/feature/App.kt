package org.halulkin.feature

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.request.crossfade
import coil3.util.DebugLogger
import org.halulkin.designsystem.theme.CinemaTheme
import org.halulkin.feature.di.appModule
import org.halulkin.feature.ui.navigation.MainScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    KoinApplication(
        application = {
            modules(appModule())
        }
    ) {
        SingletonImageLoader.setSafe { context ->
            ImageLoader.Builder(context)
                .crossfade(true)
                .logger(DebugLogger())
                .build()
        }
        CinemaTheme {
            MainScreen()
        }
    }
}