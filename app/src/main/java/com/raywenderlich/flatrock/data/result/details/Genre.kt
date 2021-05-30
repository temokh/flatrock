package com.raywenderlich.flatrock.data.result.details


import android.os.Parcelable
import com.squareup.moshi.Json
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Genre(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String
):Parcelable