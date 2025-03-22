package org.halulkin.feature.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector
import cinema_kmp.composeapp.generated.resources.Res
import cinema_kmp.composeapp.generated.resources.favorite
import cinema_kmp.composeapp.generated.resources.home
import org.jetbrains.compose.resources.StringResource

sealed class BottomNavItem(
    val route: String,
    val title: StringResource,
    val icon: ImageVector,
) {
    data object Home : BottomNavItem(
        "Home",
        Res.string.home,
        Icons.Rounded.Home
    )

    data object Favorite : BottomNavItem(
        "Favorite",
        Res.string.favorite,
        Icons.Rounded.Favorite
    )
}