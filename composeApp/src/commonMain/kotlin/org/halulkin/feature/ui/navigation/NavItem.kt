package org.halulkin.feature.ui.navigation

import kotlinx.serialization.Serializable
import org.halulkin.feature.domain.model.MovieType

@Serializable
sealed interface NavItem {

    @Serializable
    data class MovieDetails(val movieId: Int) : NavItem

    @Serializable
    data class MovieList(val moviesType: MovieType) : NavItem
}
