package org.halulkin.feature.data.persistence

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import org.halulkin.feature.data.persistence.dao.FavoriteMovieDao
import org.halulkin.feature.data.persistence.entitiy.MovieEntity


@Database(entities = [MovieEntity::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getFavoriteDao(): FavoriteMovieDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}

internal const val databaseName = "favorite_movies.db"
