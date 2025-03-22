package org.halulkin.feature.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    @SerialName(value = "page") val page: Int,
    @SerialName(value = "results") val movies: List<MovieDTO>,
    @SerialName(value = "total_pages") val totalPages: Int,
    @SerialName(value = "total_results") val totalResults: Int,
)