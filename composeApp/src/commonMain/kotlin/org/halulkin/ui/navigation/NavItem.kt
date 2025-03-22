package org.halulkin.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface NavItem {

    @Serializable
    data class MovieDetails(val movieId: Int) : NavItem
}
