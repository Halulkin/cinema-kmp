package org.halulkin.feature.domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class MovieType {
    Popular,
    TopRated,
    Trending,
}
