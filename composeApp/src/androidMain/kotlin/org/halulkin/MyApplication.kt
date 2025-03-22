package org.halulkin

import android.app.Application
import org.halulkin.feature.di.commonModule
import org.halulkin.feature.di.platformModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(androidContext = this@MyApplication)
            modules(platformModule(), commonModule())
        }
    }
}