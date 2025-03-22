package org.halulkin.feature.domain.model

data class Movie(
    val id: Int,
    val name: String,
    val image: String,
    val overview: String,
    val voteAverage: Double,
    val voteCount: Int,
    val isFavorite: Boolean = false,
)