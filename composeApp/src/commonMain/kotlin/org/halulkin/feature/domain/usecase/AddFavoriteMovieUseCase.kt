package org.halulkin.feature.domain.usecase

import org.halulkin.core.extensions.suspendRunCatching
import org.halulkin.feature.domain.model.Movie
import org.halulkin.feature.domain.repository.MovieRepository

class AddFavoriteMovieUseCase(
    private val moviesRepository: MovieRepository,
) {
    suspend operator fun invoke(movie: Movie) = suspendRunCatching {
        moviesRepository.addFavorite(movie)
    }
}