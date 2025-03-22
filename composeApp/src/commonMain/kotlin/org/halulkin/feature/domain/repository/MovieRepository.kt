package org.halulkin.feature.domain.repository

import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.halulkin.feature.domain.model.Movie
import org.halulkin.feature.domain.model.MovieType

interface MovieRepository {
    suspend fun getByMediaType(movieType: MovieType): Result<Flow<PagingData<Movie>>>
}