package org.halulkin.feature.data.utils

import org.halulkin.feature.data.persistence.AppDatabase

expect class DatabaseFactory {
    fun createDatabase(): AppDatabase
}
