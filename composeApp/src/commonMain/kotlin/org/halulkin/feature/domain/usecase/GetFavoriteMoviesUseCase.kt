package org.halulkin.feature.domain.usecase

import org.halulkin.feature.domain.repository.FavoriteMovieRepository

class GetFavoriteMoviesUseCase(
    private val favoriteMovieRepository: FavoriteMovieRepository
) {
    operator fun invoke() = runCatching {
        favoriteMovieRepository.getMovies()
    }
}
