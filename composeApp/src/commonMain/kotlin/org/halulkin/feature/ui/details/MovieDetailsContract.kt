package org.halulkin.feature.ui.details

import org.halulkin.feature.domain.model.Movie
import org.halulkin.feature.domain.model.Movie.Companion.MockMovie

data class MovieDetailsState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val movie: Movie = MockMovie,
)

data class MovieDetailsActions(
    val onBackClick: () -> Unit = {},
    val onFavoriteClick: () -> Unit = {},
)
