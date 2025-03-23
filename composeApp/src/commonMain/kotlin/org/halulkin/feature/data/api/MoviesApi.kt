package org.halulkin.feature.data.api

import androidx.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import org.halulkin.core.utils.Constants
import org.halulkin.feature.data.api.model.MovieDTO
import org.halulkin.feature.data.api.model.MoviesResponse
import org.halulkin.feature.data.api.model.toMovie
import org.halulkin.feature.data.utils.MoviesPagingSource
import org.halulkin.feature.domain.model.Movie

class MoviesApi(
    private val httpClient: HttpClient
) {
    suspend fun getMovieById(id: Int): Movie {
        return httpClient
            .get(urlString = "movie/$id")
            .body<MovieDTO>()
            .toMovie()
    }

    fun getPopularMovies(): Flow<PagingData<Movie>> = fetchPagedMovies { page ->
        httpClient.get(urlString = "movie/popular") {
            parameter("page", page)
        }.body<MoviesResponse>().movies.map(MovieDTO::toMovie)
    }

    fun getTrendingMovies(): Flow<PagingData<Movie>> = fetchPagedMovies { page ->
        httpClient.get(urlString = "trending/movie/day") {
            parameter("page", page)
        }.body<MoviesResponse>().movies.map(MovieDTO::toMovie)
    }

    fun getTopRatedMovies(): Flow<PagingData<Movie>> = fetchPagedMovies { page ->
        httpClient.get(urlString = "movie/top_rated") {
            parameter("page", page)
        }.body<MoviesResponse>().movies.map(MovieDTO::toMovie)
    }

    private fun fetchPagedMovies(
        fetch: suspend (Int) -> List<Movie>,
    ): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(
            pageSize = Constants.PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { MoviesPagingSource(fetch) }
    ).flow
}
