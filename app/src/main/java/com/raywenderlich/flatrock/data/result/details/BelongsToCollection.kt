package com.raywenderlich.flatrock.data.result.details


import android.os.Parcelable
import com.squareup.moshi.Json
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
data class BelongsToCollection(
    @Json(name = "backdrop_path")
    val backdropPath: Any?,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "poster_path")
    val posterPath: Any?
)