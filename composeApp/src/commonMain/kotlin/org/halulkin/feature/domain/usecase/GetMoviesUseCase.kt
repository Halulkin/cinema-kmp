package org.halulkin.feature.domain.usecase

import org.halulkin.feature.domain.model.MovieType
import org.halulkin.feature.domain.repository.MovieRepository

class GetMoviesUseCase(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(moviesType: MovieType) = runCatching {
        movieRepository.getByMediaType(moviesType)
    }
}
