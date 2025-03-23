package org.halulkin.feature.domain.usecase

import GetMoviesUseCase
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
import kotlin.coroutines.cancellation.CancellationException
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class GetMoviesUseCaseTest {

    private lateinit var movieRepository: MovieRepository
    private lateinit var getMoviesUseCase: GetMoviesUseCase

    @BeforeTest
    fun setup() {
        movieRepository = mockk()
        getMoviesUseCase = GetMoviesUseCase(movieRepository)
    }

    @Test
    fun `return success result with movies flow when repository succeeds`() = runTest {
        // Given
        val movieType = MovieType.TopRated
        val mockMoviesFlow = flowOf(
            PagingData.from(listOf(MockMovie, MockMovie))
        )

        coEvery { movieRepository.getByMediaType(movieType) } returns mockMoviesFlow

        // When
        val result = getMoviesUseCase(movieType)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(mockMoviesFlow, result.getOrNull())
    }

    @Test
    fun `return failure result when repository throws exception`() = runTest {
        // Given
        val movieType = MovieType.Trending
        val networkError = IOException("Network error")

        coEvery { movieRepository.getByMediaType(movieType) } throws networkError

        // When
        val result = getMoviesUseCase(movieType)

        // Then
        assertTrue(result.isFailure)
        assertEquals(networkError, result.exceptionOrNull())
    }

    @Test
    fun `propagate CancellationException without wrapping it`() = runTest {
        // Given
        val movieType = MovieType.Popular
        val cancellationException = CancellationException("Operation cancelled")

        coEvery { movieRepository.getByMediaType(movieType) } throws cancellationException

        // When - Then: Verify CancellationException is thrown
        assertFailsWith<CancellationException> {
            getMoviesUseCase(movieType)
        }
    }
}
