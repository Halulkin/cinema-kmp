package org.halulkin.feature.domain.repository

import org.halulkin.feature.domain.model.Movie

interface MovieDetailsRepository {
    suspend fun getMovieById(id: Int): Movie
}
