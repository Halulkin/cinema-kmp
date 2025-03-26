package org.halulkin.feature.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface NavItem {

    @Serializable
    data class MovieDetails(val movieId: Int) : NavItem

    @Serializable
    data class MovieList(val moviesType: String) : NavItem
}
