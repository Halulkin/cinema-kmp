package org.halulkin.feature.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.halulkin.feature.data.persistence.AppDatabase
import org.halulkin.feature.data.persistence.entitiy.MovieEntity
import org.halulkin.feature.data.persistence.entitiy.toMovie
import org.halulkin.feature.domain.model.Movie
import org.halulkin.feature.domain.model.toMovieEntity
import org.halulkin.feature.domain.repository.FavoriteMovieRepository

class FavoriteMovieRepositoryImp(
    private val database: AppDatabase
) : FavoriteMovieRepository {

    override suspend fun getMovies(): Flow<List<Movie>> {
        return database.getFavoriteDao()
            .getAll()
            .map { it.map(MovieEntity::toMovie) }
    }

    override suspend fun saveFavorite(movie: Movie) {
        database.getFavoriteDao()
            .insert(movie.toMovieEntity())
    }

    override suspend fun removeFavorite(id: Int) {
        database.getFavoriteDao().deleteById(id)
    }
}
