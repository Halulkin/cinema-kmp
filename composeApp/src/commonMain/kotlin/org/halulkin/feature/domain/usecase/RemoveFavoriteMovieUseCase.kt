package org.halulkin.feature.domain.usecase

import org.halulkin.core.extensions.suspendRunCatching
import org.halulkin.feature.domain.repository.MovieRepository

class RemoveFavoriteMovieUseCase(
    private val moviesRepository: MovieRepository,
) {
    suspend operator fun invoke(id: Int) = suspendRunCatching {
        moviesRepository.removeFavorite(id)
    }
}
