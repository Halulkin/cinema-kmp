package org.halulkin.feature.data.repository

import org.halulkin.feature.data.api.MoviesApi
import org.halulkin.feature.data.persistence.AppDatabase
import org.halulkin.feature.data.persistence.entitiy.toMovie
import org.halulkin.feature.domain.model.Movie
import org.halulkin.feature.domain.repository.MovieDetailsRepository

class MovieDetailsRepositoryImpl(
    private val remote: MoviesApi,
    private val local: AppDatabase
) : MovieDetailsRepository {

    override suspend fun getMovieById(id: Int): Movie {
        val movieEntity = local.getFavoriteDao().getById(id)
        return movieEntity?.toMovie() ?: remote.getMovieById(id)
    }
}
