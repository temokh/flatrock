package com.raywenderlich.flatrock.storage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavMoviesViewModel(application: Application): AndroidViewModel(application) {

    val readAlldata: LiveData<List<FavMovies>>
    private val repository: FavMovieRepository


    init {

        val favMoviesDao = FavMoviesDatabase.getDatabase(application).favMoviesDao()
        repository = FavMovieRepository(favMoviesDao)
        readAlldata = repository.readAllData

    }

    fun addMovies(favmovies: FavMovies){
        viewModelScope.launch(Dispatchers.IO){
            repository.addFavMovies(favmovies)
        }
    }


}