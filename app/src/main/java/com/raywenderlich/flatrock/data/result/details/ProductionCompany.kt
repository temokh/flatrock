package com.raywenderlich.flatrock.data.result.details


import com.squareup.moshi.Json
import androidx.annotation.Keep

@Keep
data class ProductionCompany(
    @Json(name = "id")
    val id: Int,
    @Json(name = "logo_path")
    val logoPath: Any?,
    @Json(name = "name")
    val name: String,
    @Json(name = "origin_country")
    val originCountry: String
)