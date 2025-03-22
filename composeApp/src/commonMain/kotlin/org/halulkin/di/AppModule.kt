package org.halulkin.di


import org.halulkin.core.KtorHttpClient
import org.halulkin.data.api.MoviesApi
import org.halulkin.data.repository.MoviesRepositoryImpl
import org.halulkin.domain.repository.MovieRepository
import org.halulkin.domain.usecase.GetMoviesUseCase
import org.halulkin.ui.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun appModule() = module {

    single { KtorHttpClient.httpClient() }
    single { MoviesApi(get()) }
    single<MovieRepository> { MoviesRepositoryImpl(get()) }
    single { GetMoviesUseCase(get()) }

    viewModelOf(::HomeViewModel)
}
