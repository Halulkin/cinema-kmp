package org.halulkin.feature.di

import org.halulkin.feature.data.utils.DatabaseFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { DatabaseFactory(context = get()).createDatabase() }
}