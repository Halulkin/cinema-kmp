package org.halulkin.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.halulkin.domain.model.Movie
import org.halulkin.domain.model.MovieType

interface MovieRepository {
    suspend fun getByMediaType(movieType: MovieType): Result<Flow<PagingData<Movie>>>
}