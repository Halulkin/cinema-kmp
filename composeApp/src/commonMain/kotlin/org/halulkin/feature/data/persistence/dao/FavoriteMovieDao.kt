package org.halulkin.feature.data.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.halulkin.feature.data.persistence.entitiy.MovieEntity

@Dao
interface FavoriteMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieEntity)

    @Query("SELECT * FROM `favorite_movies`")
    fun getAll(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM `favorite_movies` WHERE id = :id")
    suspend fun getById(id: Int): MovieEntity?

    @Query("DELETE FROM `favorite_movies` WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM `favorite_movies`")
    suspend fun deleteAll()
}
