package org.halulkin.feature.domain.model

data class Movie(
    val id: Int,
    val name: String,
    val image: String,
    val backdrop: String,
    val overview: String,
    val voteAverage: Double,
    val voteCount: Int,
    val isFavorite: Boolean = false,
) {
    companion object {
        val MockMovie = Movie(
            id = 0,
            name = "",
            image = "",
            backdrop = "",
            overview = "",
            voteAverage = 0.0,
            voteCount = 0,
            isFavorite = false,
        )
    }
}