package com.raywenderlich.flatrock.storage

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "fav")
class FavMovies(
    @PrimaryKey
    val id: Int,
    val img: String) {
}