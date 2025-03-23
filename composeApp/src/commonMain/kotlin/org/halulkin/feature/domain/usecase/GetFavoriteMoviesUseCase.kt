package org.halulkin.feature.domain.usecase

import org.halulkin.core.extensions.suspendRunCatching
import org.halulkin.feature.domain.repository.FavoriteMovieRepository

class GetFavoriteMoviesUseCase(
    private val favoriteMovieRepository: FavoriteMovieRepository
) {
    suspend operator fun invoke() = suspendRunCatching {
        favoriteMovieRepository.getMovies()
    }
}
