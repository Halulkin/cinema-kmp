package org.halulkin.feature.domain.usecase

import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlinx.io.IOException
import org.halulkin.feature.domain.model.Movie
import org.halulkin.feature.domain.model.Movie.Companion.MockMovie
import org.halulkin.feature.domain.repository.FavoriteMovieRepository
import kotlin.coroutines.cancellation.CancellationException
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class GetFavoriteMoviesUseCaseTest {

    private lateinit var favoriteMovieRepository: FavoriteMovieRepository
    private lateinit var getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase

    @BeforeTest
    fun setup() {
        favoriteMovieRepository = mockk()
        getFavoriteMoviesUseCase = GetFavoriteMoviesUseCase(favoriteMovieRepository)
    }

    @Test
    fun `return success result with movies flow when repository succeeds`() = runTest {
        // Given
        val mockMoviesFlow = flowOf(listOf(MockMovie, MockMovie))
        coEvery { favoriteMovieRepository.getMovies() } returns mockMoviesFlow

        // When
        val result = getFavoriteMoviesUseCase()

        // Then
        assertTrue(result.isSuccess)
        assertEquals(mockMoviesFlow, result.getOrNull())
    }

    @Test
    fun `return empty list of favorite movies`() = runTest {
        // Given
        val emptyMoviesFlow = flowOf(emptyList<Movie>())
        coEvery { favoriteMovieRepository.getMovies() } returns emptyMoviesFlow

        // When
        val result = getFavoriteMoviesUseCase()

        // Then
        assertTrue(result.isSuccess)
        assertEquals(emptyMoviesFlow, result.getOrNull())
    }

    @Test
    fun `returns movies sequentially in flow`() = runTest {
        // Given
        val movie1 = MockMovie.copy(id = 1)
        val movie2 = MockMovie.copy(id = 2)
        val initialMovies = listOf(movie1)
        val updatedMovies = listOf(movie1, movie2)

        val favoriteMoviesFlow = flowOf(initialMovies, updatedMovies)

        coEvery { favoriteMovieRepository.getMovies() } returns favoriteMoviesFlow

        // When - Then: Verify movies are emitted sequentially
        favoriteMoviesFlow.test {
            assertEquals(initialMovies, awaitItem())
            assertEquals(updatedMovies, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `return failure result when repository throws exception`() = runTest {
        // Given
        val databaseError = IOException("Database error")
        coEvery { favoriteMovieRepository.getMovies() } throws databaseError

        // When
        val result = getFavoriteMoviesUseCase()

        // Then
        assertTrue(result.isFailure)
        assertEquals(databaseError, result.exceptionOrNull())
    }

    @Test
    fun `invoke should rethrow CancellationException`() = runTest {
        // Given
        val cancellationException = CancellationException("Operation cancelled")
        coEvery { favoriteMovieRepository.getMovies() } throws cancellationException

        // When - Then: Verify CancellationException is thrown
        assertFailsWith<CancellationException> {
            getFavoriteMoviesUseCase()
        }
    }
}