package com.raywenderlich.flatrock.storage

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface FavMoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovies(favMovies: FavMovies)

    @Query("SELECT * FROM fav")
    fun readAllData():LiveData<List<FavMovies>>


}