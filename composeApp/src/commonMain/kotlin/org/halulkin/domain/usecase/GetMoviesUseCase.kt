package org.halulkin.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.halulkin.domain.model.Movie
import org.halulkin.domain.model.MovieType
import org.halulkin.domain.repository.MovieRepository

class GetMoviesUseCase(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(moviesType: MovieType): Result<Flow<PagingData<Movie>>> {
        return movieRepository.getByMediaType(moviesType)
    }
}