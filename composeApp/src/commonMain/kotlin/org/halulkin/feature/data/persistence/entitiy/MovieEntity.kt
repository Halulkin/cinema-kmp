package org.halulkin.feature.data.persistence.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.halulkin.feature.domain.model.Movie

@Entity(tableName = "favorite_movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    val name: String,
    val image: String,
    val overview: String,
    val voteAverage: Double,
    val voteCount: Int,
)

fun MovieEntity.toMovie() = Movie(
    id = id,
    name = name,
    image = image,
    overview = overview,
    voteAverage = voteAverage,
    voteCount = voteCount,
    isFavorite = true,
)