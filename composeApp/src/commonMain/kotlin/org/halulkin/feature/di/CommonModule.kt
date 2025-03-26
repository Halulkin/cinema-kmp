package org.halulkin.feature.di

import org.halulkin.core.network.KtorHttpClient
import org.halulkin.feature.data.api.MoviesApi
import org.halulkin.feature.data.repository.FavoriteMovieRepositoryImp
import org.halulkin.feature.data.repository.MovieDetailsRepositoryImpl
import org.halulkin.feature.data.repository.MoviesRepositoryImpl
import org.halulkin.feature.domain.repository.FavoriteMovieRepository
import org.halulkin.feature.domain.repository.MovieDetailsRepository
import org.halulkin.feature.domain.repository.MovieRepository
import org.halulkin.feature.domain.usecase.GetFavoriteMoviesUseCase
import org.halulkin.feature.domain.usecase.GetMovieUseCase
import org.halulkin.feature.domain.usecase.GetMoviesUseCase
import org.halulkin.feature.domain.usecase.GetPagingMoviesUseCase
import org.halulkin.feature.domain.usecase.ToggleFavoriteMovieUseCase
import org.halulkin.feature.ui.details.MovieDetailsViewModel
import org.halulkin.feature.ui.favorite.FavoriteViewModel
import org.halulkin.feature.ui.home.HomeViewModel
import org.halulkin.feature.ui.movielist.MovieListViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun commonModule() = module {
    single { KtorHttpClient.httpClient() }
    single { MoviesApi(get()) }

    single<MovieRepository> { MoviesRepositoryImpl(remote = get()) }
    single<FavoriteMovieRepository> { FavoriteMovieRepositoryImp(database = get()) }
    single<MovieDetailsRepository> { MovieDetailsRepositoryImpl(remote = get(), local = get()) }

    single { GetMoviesUseCase(get()) }
    single { GetPagingMoviesUseCase(get()) }
    single { GetMovieUseCase(get()) }
    single { ToggleFavoriteMovieUseCase(get()) }
    single { GetFavoriteMoviesUseCase(get()) }

    viewModelOf(::HomeViewModel)
    viewModelOf(::MovieDetailsViewModel)
    viewModelOf(::FavoriteViewModel)
    viewModelOf(::MovieListViewModel)
}

expect fun platformModule(): Module
