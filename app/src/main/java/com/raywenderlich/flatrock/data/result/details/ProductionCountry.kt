package com.raywenderlich.flatrock.data.result.details


import com.squareup.moshi.Json
import androidx.annotation.Keep

@Keep
data class ProductionCountry(
    @Json(name = "iso_3166_1")
    val iso31661: String,
    @Json(name = "name")
    val name: String
)