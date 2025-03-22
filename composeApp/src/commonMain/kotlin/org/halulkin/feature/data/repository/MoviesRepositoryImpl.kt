package org.halulkin.feature.data.repository

import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.halulkin.feature.data.api.MoviesApi
import org.halulkin.feature.domain.model.Movie
import org.halulkin.feature.domain.model.MovieType
import org.halulkin.feature.domain.repository.MovieRepository

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

    override suspend fun getMovieById(id: Int): Movie {
        return api.getMovieById(id)
    }
}