package org.halulkin

import android.app.Application
import org.halulkin.feature.di.initKoin
import org.koin.android.ext.koin.androidContext

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@MyApplication)
        }
    }
}