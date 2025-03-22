package org.halulkin.data.api

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import org.halulkin.data.api.model.MovieDTO
import org.halulkin.data.api.model.MoviesResponse
import org.halulkin.data.api.model.toMovie
import org.halulkin.data.utils.MoviesPagingSource
import org.halulkin.domain.model.Movie
import org.halulkin.domain.utils.Constants.PAGE_SIZE

class MoviesApi(
    private val httpClient: HttpClient
) {
    fun getPopularMovies(): Result<Flow<PagingData<Movie>>> = runCatching {
        fetchPagedMovies { page ->
            httpClient.get(urlString = "movie/popular") {
                parameter("page", page)
            }.body<MoviesResponse>().movies.map(MovieDTO::toMovie)
        }
    }

    fun getTrendingMovies(): Result<Flow<PagingData<Movie>>> = runCatching {
        fetchPagedMovies { page ->
            httpClient.get(urlString = "trending/movie/day") {
                parameter("page", page)
            }.body<MoviesResponse>().movies.map(MovieDTO::toMovie)
        }
    }

    fun getTopRatedMovies(): Result<Flow<PagingData<Movie>>> = runCatching {
        fetchPagedMovies { page ->
            httpClient.get(urlString = "movie/top_rated") {
                parameter("page", page)
            }.body<MoviesResponse>().movies.map(MovieDTO::toMovie)
        }
    }

    private fun fetchPagedMovies(
        fetch: suspend (Int) -> List<Movie>,
    ): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviesPagingSource(fetch) }
        ).flow
    }
}