package org.halulkin.feature.domain.usecase

import org.halulkin.core.extensions.suspendRunCatching
import org.halulkin.feature.domain.repository.FavoriteMovieRepository

class RemoveFavoriteMovieUseCase(
    private val repository: FavoriteMovieRepository,
) {
    suspend operator fun invoke(id: Int) = suspendRunCatching {
        repository.removeFavorite(id)
    }
}
