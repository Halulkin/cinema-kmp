package org.halulkin.feature.data.utils

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import org.halulkin.feature.data.persistence.AppDatabase
import org.halulkin.feature.data.persistence.databaseName

actual class DatabaseFactory(private val context: Context) {
    actual fun createDatabase(): AppDatabase {
        val appContext = context.applicationContext

        val dbFile = appContext.getDatabasePath(databaseName)
        return Room.databaseBuilder<AppDatabase>(
            context = appContext,
            name = dbFile.absolutePath
        )
            .setQueryCoroutineContext(Dispatchers.IO)
            .fallbackToDestructiveMigration(true)
            .setDriver(BundledSQLiteDriver())
            .build()
    }
}
