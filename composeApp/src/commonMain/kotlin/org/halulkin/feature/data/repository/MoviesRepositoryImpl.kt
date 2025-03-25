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
    override suspend fun getMoviesByType(movieType: MovieType): List<Movie> =
        remote.getMoviesByEndpoint(movieType.toEndpoint())

    override fun getPagingMoviesByType(movieType: MovieType): Flow<PagingData<Movie>> =
        remote.getPagingMoviesByEndpoint(movieType.toEndpoint())
}

private fun MovieType.toEndpoint(): String = when (this) {
    MovieType.Trending -> "trending/movie/day"
    MovieType.Popular -> "movie/popular"
    MovieType.TopRated -> "movie/top_rated"
}