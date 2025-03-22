package org.halulkin.feature.domain.model

import org.halulkin.feature.data.persistence.entitiy.MovieEntity

data class Movie(
    val id: Int,
    val name: String,
    val image: String,
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
            overview = "",
            voteAverage = 0.0,
            voteCount = 0,
            isFavorite = false,
        )
    }
}

fun Movie.toMovieEntity() = MovieEntity(
    id = id,
    name = name,
    image = image,
    overview = overview,
    voteAverage = voteAverage,
    voteCount = voteCount,
)
