package org.halulkin.feature.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.halulkin.feature.domain.model.Movie
import org.halulkin.core.utils.Constants.POSTER_BASE_URL

@Serializable
data class MovieDTO(
    @SerialName("id") val id: Int? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("original_language") val originalLanguage: String? = null,
    @SerialName("original_title") val originalTitle: String? = null,
    @SerialName("overview") val overview: String? = null,
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("media_type") val mediaType: String? = null,
    @SerialName("popularity") val popularity: Double? = null,
    @SerialName("first_air_date") val firstAirDate: String? = null,
    @SerialName("vote_average") val voteAverage: Double? = null,
    @SerialName("vote_count") val voteCount: Int? = null,
    @SerialName("origin_country") val originCountry: ArrayList<String> = arrayListOf(),
    @SerialName("adult") val adult: Boolean? = null,
    @SerialName("backdrop_path") val backdropPath: String? = null,
)

fun MovieDTO.toMovie(isFavorite: Boolean = false) = Movie(
    id = id ?: 0,
    name = title ?: "Unknown",
    image = (POSTER_BASE_URL + posterPath),
    overview = overview ?: "",
    voteAverage = voteAverage ?: 0.0,
    voteCount = voteCount ?: 0,
    isFavorite = isFavorite,
)