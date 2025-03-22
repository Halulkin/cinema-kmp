package org.halulkin.feature.domain.usecase

import org.halulkin.core.extensions.suspendRunCatching
import org.halulkin.feature.domain.repository.MovieDetailsRepository

class GetMovieUseCase(
    private val repository: MovieDetailsRepository,
) {
    suspend operator fun invoke(id: Int) = suspendRunCatching {
        repository.getMovieById(id)
    }
}