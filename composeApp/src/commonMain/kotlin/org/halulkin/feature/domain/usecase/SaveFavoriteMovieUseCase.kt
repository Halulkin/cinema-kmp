package org.halulkin.feature.domain.usecase

import org.halulkin.core.extensions.suspendRunCatching
import org.halulkin.feature.domain.model.Movie
import org.halulkin.feature.domain.repository.FavoriteMovieRepository

class SaveFavoriteMovieUseCase(
    private val repository: FavoriteMovieRepository,
) {
    suspend operator fun invoke(movie: Movie) = suspendRunCatching {
        repository.saveFavorite(movie)
    }
}