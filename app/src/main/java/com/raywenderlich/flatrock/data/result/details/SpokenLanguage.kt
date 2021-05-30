package com.raywenderlich.flatrock.data.result.details


import com.squareup.moshi.Json
import androidx.annotation.Keep

@Keep
data class SpokenLanguage(
    @Json(name = "english_name")
    val englishName: String,
    @Json(name = "iso_639_1")
    val iso6391: String,
    @Json(name = "name")
    val name: String
)