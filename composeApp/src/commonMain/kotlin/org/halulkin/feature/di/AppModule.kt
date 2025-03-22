package org.halulkin.feature.di

import org.halulkin.core.network.KtorHttpClient
import org.halulkin.feature.data.api.MoviesApi
import org.halulkin.feature.data.repository.MoviesRepositoryImpl
import org.halulkin.feature.domain.repository.MovieRepository
import org.halulkin.feature.domain.usecase.GetMovieUseCase
import org.halulkin.feature.domain.usecase.GetMoviesUseCase
import org.halulkin.feature.ui.details.MovieDetailsViewModel
import org.halulkin.feature.ui.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun appModule() = module {
    single { KtorHttpClient.httpClient() }
    single { MoviesApi(get()) }

    single<MovieRepository> { MoviesRepositoryImpl(get()) }

    single { GetMoviesUseCase(get()) }
    single { GetMovieUseCase(get()) }

    viewModelOf(::HomeViewModel)
    viewModelOf(::MovieDetailsViewModel)
}
