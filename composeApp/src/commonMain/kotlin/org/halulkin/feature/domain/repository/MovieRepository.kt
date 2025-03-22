package org.halulkin.feature.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.halulkin.feature.domain.model.Movie
import org.halulkin.feature.domain.model.MovieType

interface MovieRepository {
    suspend fun getByMediaType(movieType: MovieType): Result<Flow<PagingData<Movie>>>
    suspend fun getMovieById(id: Int): Movie
    suspend fun addFavorite(movie: Movie)
    suspend fun removeFavorite(id: Int)
}