package org.halulkin.feature.data.repository

import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.halulkin.feature.data.api.MoviesApi
import org.halulkin.feature.domain.model.Movie
import org.halulkin.feature.domain.model.MovieType
import org.halulkin.feature.domain.repository.MovieRepository

class MoviesRepositoryImpl(
    private val remote: MoviesApi,
) : MovieRepository {
    override suspend fun getByMediaType(movieType: MovieType): Flow<PagingData<Movie>> {
        return when (movieType) {
            MovieType.Trending -> remote.getTrendingMovies()
            MovieType.Popular -> remote.getPopularMovies()
            MovieType.TopRated -> remote.getTopRatedMovies()
        }
    }
}
