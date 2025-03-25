package org.halulkin.feature.domain.repository

import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.halulkin.feature.domain.model.Movie
import org.halulkin.feature.domain.model.MovieType

interface MovieRepository {
    suspend fun getMoviesByType(movieType: MovieType): List<Movie>
    fun getPagingMoviesByType(movieType: MovieType): Flow<PagingData<Movie>>
}
