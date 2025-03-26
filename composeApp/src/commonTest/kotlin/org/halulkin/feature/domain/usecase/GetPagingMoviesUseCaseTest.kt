package org.halulkin.feature.domain.usecase

import app.cash.paging.PagingData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlinx.io.IOException
import org.halulkin.feature.domain.model.Movie.Companion.MockMovie
import org.halulkin.feature.domain.model.MovieType
import org.halulkin.feature.domain.repository.MovieRepository
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class GetPagingMoviesUseCaseTest {

    private lateinit var movieRepository: MovieRepository
    private lateinit var getPagingMoviesUseCase: GetPagingMoviesUseCase

    @BeforeTest
    fun setup() {
        movieRepository = mockk()
        getPagingMoviesUseCase = GetPagingMoviesUseCase(movieRepository)
    }

    @Test
    fun `return success result with movies flow when repository succeeds`() = runTest {
        // Given
        val movieType = MovieType.TopRated
        val mockMoviesFlow = flowOf(
            PagingData.from(listOf(MockMovie, MockMovie))
        )

        coEvery { movieRepository.getPagingMoviesByType(movieType) } returns mockMoviesFlow

        // When
        val result = getPagingMoviesUseCase(movieType)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(mockMoviesFlow, result.getOrNull())
    }

    @Test
    fun `return failure result when repository throws exception`() = runTest {
        // Given
        val movieType = MovieType.Trending
        val networkError = IOException("Network error")

        coEvery { movieRepository.getPagingMoviesByType(movieType) } throws networkError

        // When
        val result = getPagingMoviesUseCase(movieType)

        // Then
        assertTrue(result.isFailure)
        assertEquals(networkError, result.exceptionOrNull())
    }
}
