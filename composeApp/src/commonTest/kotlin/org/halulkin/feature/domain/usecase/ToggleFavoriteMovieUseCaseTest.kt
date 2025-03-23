package org.halulkin.feature.domain.usecase

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.halulkin.feature.domain.model.Movie
import org.halulkin.feature.domain.repository.FavoriteMovieRepository
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class ToggleFavoriteMovieUseCaseTest {

    private lateinit var favoriteMovieRepository: FavoriteMovieRepository
    private lateinit var toggleFavoriteMovieUseCase: ToggleFavoriteMovieUseCase

    @BeforeTest
    fun setup() {
        favoriteMovieRepository = mockk(relaxed = true)
        toggleFavoriteMovieUseCase = ToggleFavoriteMovieUseCase(favoriteMovieRepository)
    }

    @Test
    fun `remove movie from favorites when movie is already favorite`() = runTest {
        // Given
        val favoriteMovie = Movie.MockMovie.copy(isFavorite = true)
        coEvery { favoriteMovieRepository.removeFavorite(favoriteMovie.id) } returns Unit

        // When
        val result = toggleFavoriteMovieUseCase(favoriteMovie)

        // Then
        assertTrue(result.isSuccess)
        coVerify(exactly = 1) { favoriteMovieRepository.removeFavorite(favoriteMovie.id) }
        coVerify(exactly = 0) { favoriteMovieRepository.saveFavorite(any()) }
        confirmVerified(favoriteMovieRepository)
    }

    @Test
    fun `add movie to favorites when movie is not favorite`() = runTest {
        // Given
        val nonFavoriteMovie = Movie.MockMovie.copy(isFavorite = false)
        coEvery { favoriteMovieRepository.saveFavorite(nonFavoriteMovie) } returns Unit

        // When
        val result = toggleFavoriteMovieUseCase(nonFavoriteMovie)

        // Then
        assertTrue(result.isSuccess)
        coVerify(exactly = 0) { favoriteMovieRepository.removeFavorite(any()) }
        coVerify(exactly = 1) { favoriteMovieRepository.saveFavorite(nonFavoriteMovie) }
        confirmVerified(favoriteMovieRepository)
    }

    @Test
    fun `increase favorite movie count when adding a non-favorite movie`() = runTest {
        // Given
        val nonFavoriteMovie = Movie.MockMovie.copy(isFavorite = false)
        val emptyList = emptyList<Movie>()
        val listWithMovie = listOf(nonFavoriteMovie.copy(isFavorite = true))

        // Mock initial empty state and subsequent state with added movie
        val moviesFlow = MutableStateFlow(emptyList)
        coEvery { favoriteMovieRepository.getMovies() } returns moviesFlow
        coEvery { favoriteMovieRepository.saveFavorite(nonFavoriteMovie) } answers {
            moviesFlow.value = listWithMovie
        }

        // When - Check initial count
        val initialFavorites = favoriteMovieRepository.getMovies().first()
        assertTrue(initialFavorites.isEmpty())

        // When - Call usecase
        val result = toggleFavoriteMovieUseCase(nonFavoriteMovie)

        // Then - Verify count increased
        assertTrue(result.isSuccess)
        val updatedFavorites = favoriteMovieRepository.getMovies().first()
        assertTrue(updatedFavorites.size == 1)
        coVerify(exactly = 1) { favoriteMovieRepository.saveFavorite(nonFavoriteMovie) }
    }

    @Test
    fun `return failure when repository throws exception while removing favorite`() = runTest {
        // Given
        val favoriteMovie = Movie.MockMovie.copy(isFavorite = true)
        val exception = RuntimeException("Database error")
        coEvery { favoriteMovieRepository.removeFavorite(favoriteMovie.id) } throws exception

        // When
        val result = toggleFavoriteMovieUseCase(favoriteMovie)

        // Then
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is RuntimeException)
        coVerify(exactly = 1) { favoriteMovieRepository.removeFavorite(favoriteMovie.id) }
        confirmVerified(favoriteMovieRepository)
    }

    @Test
    fun `return failure when repository throws exception while saving favorite`() = runTest {
        // Given
        val nonFavoriteMovie = Movie.MockMovie.copy(isFavorite = false)
        val exception = RuntimeException("Database error")
        coEvery { favoriteMovieRepository.saveFavorite(nonFavoriteMovie) } throws exception

        // When
        val result = toggleFavoriteMovieUseCase(nonFavoriteMovie)

        // Then
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is RuntimeException)
        coVerify(exactly = 1) { favoriteMovieRepository.saveFavorite(nonFavoriteMovie) }
        confirmVerified(favoriteMovieRepository)
    }
}
