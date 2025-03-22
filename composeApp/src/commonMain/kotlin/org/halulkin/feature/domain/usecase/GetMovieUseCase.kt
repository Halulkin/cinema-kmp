package org.halulkin.feature.domain.usecase

import org.halulkin.core.extensions.suspendRunCatching
import org.halulkin.feature.domain.repository.MovieRepository

class GetMovieUseCase(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(id: Int) = suspendRunCatching {
        movieRepository.getMovieById(id)
    }
}