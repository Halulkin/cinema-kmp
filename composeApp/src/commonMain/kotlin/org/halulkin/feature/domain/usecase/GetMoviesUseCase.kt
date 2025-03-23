package org.halulkin.feature.domain.usecase

import org.halulkin.core.extensions.suspendRunCatching
import org.halulkin.feature.domain.model.MovieType
import org.halulkin.feature.domain.repository.MovieRepository

class GetMoviesUseCase(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(moviesType: MovieType) = suspendRunCatching {
        movieRepository.getByMediaType(moviesType)
    }
}
