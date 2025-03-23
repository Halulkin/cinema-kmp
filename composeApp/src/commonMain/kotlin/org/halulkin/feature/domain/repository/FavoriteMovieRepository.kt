package org.halulkin.feature.domain.repository

import kotlinx.coroutines.flow.Flow
import org.halulkin.feature.domain.model.Movie

interface FavoriteMovieRepository {
    suspend fun getMovies(): Flow<List<Movie>>
    suspend fun saveFavorite(movie: Movie)
    suspend fun removeFavorite(id: Int)
}
