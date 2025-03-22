package org.halulkin.data.repository

import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.halulkin.data.api.MoviesApi
import org.halulkin.domain.model.Movie
import org.halulkin.domain.model.MovieType
import org.halulkin.domain.repository.MovieRepository

class MoviesRepositoryImpl(
    private val api: MoviesApi,
) : MovieRepository {
    override suspend fun getByMediaType(movieType: MovieType): Result<Flow<PagingData<Movie>>> {
        return when (movieType) {
            MovieType.Trending -> api.getTrendingMovies()
            MovieType.Popular -> api.getPopularMovies()
            MovieType.TopRated -> api.getTopRatedMovies()
        }
    }
}