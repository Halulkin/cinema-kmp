package org.halulkin.feature.data.persistence.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    val name: String,
    val image: String,
    val overview: String,
    val voteAverage: Double,
    val voteCount: Int,
)
