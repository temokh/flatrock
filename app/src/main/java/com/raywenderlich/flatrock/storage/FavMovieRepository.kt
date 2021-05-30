package com.raywenderlich.flatrock.storage

import androidx.lifecycle.LiveData

class FavMovieRepository(private val favMoviesDao: FavMoviesDao) {

    val readAllData: LiveData<List<FavMovies>> = favMoviesDao.readAllData()


    suspend fun addFavMovies(favMovies: FavMovies){
        favMoviesDao.addMovies(favMovies)
    }


}