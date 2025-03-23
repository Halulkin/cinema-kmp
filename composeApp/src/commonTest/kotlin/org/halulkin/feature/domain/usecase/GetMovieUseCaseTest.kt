package org.halulkin.feature.domain.usecase

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.io.IOException
import org.halulkin.feature.domain.model.Movie.Companion.MockMovie
import org.halulkin.feature.domain.repository.MovieDetailsRepository
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class GetMovieUseCaseTest {

    private lateinit var movieDetailsRepository: MovieDetailsRepository
    private lateinit var getMovieUseCase: GetMovieUseCase

    @BeforeTest
    fun setup() {
        movieDetailsRepository = mockk()
        getMovieUseCase = GetMovieUseCase(movieDetailsRepository)
    }

    @Test
    fun `return success result with movie when repository succeeds`() = runTest {
        // Given
        val movieId = 1
        val expectedMovie = MockMovie.copy(id = movieId)

        coEvery { movieDetailsRepository.getMovieById(movieId) } returns expectedMovie

        // When
        val result = getMovieUseCase(movieId)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(expectedMovie, result.getOrNull())
    }

    @Test
    fun `return failure result when repository fails`() = runTest {
        // Given
        val movieId = 456
        val networkError = IOException("Network error or API issue")

        coEvery { movieDetailsRepository.getMovieById(movieId) } throws networkError

        // When
        val result = getMovieUseCase(movieId)

        // Then
        assertTrue(result.isFailure)
        assertEquals(networkError, result.exceptionOrNull())
    }

    @Test
    fun `return failure result when repository fails with invalid movie ID`() = runTest {
        // Given
        val invalidMovieId = -1
        val exception = IllegalArgumentException("Invalid movie ID")

        coEvery { movieDetailsRepository.getMovieById(invalidMovieId) } throws exception

        // When
        val result = getMovieUseCase(invalidMovieId)

        // Then
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}
