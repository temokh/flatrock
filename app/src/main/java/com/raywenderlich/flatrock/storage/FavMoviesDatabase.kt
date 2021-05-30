package com.raywenderlich.flatrock.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.raywenderlich.flatrock.data.result.details.MovieDetails

@Database(entities = [FavMovies::class],version = 1, exportSchema = false)
abstract class FavMoviesDatabase: RoomDatabase() {

    abstract fun favMoviesDao(): FavMoviesDao

    companion object {
        @Volatile
        private var INSTANCE: FavMoviesDatabase? = null

        fun getDatabase(context: Context):FavMoviesDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavMoviesDatabase::class.java,
                    "fav"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }


}