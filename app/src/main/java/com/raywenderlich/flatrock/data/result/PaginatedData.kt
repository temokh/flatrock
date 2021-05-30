package com.raywenderlich.flatrock.data.result

import com.squareup.moshi.Json
import androidx.annotation.Keep

@Keep
data class PaginatedData(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<MovieResult>
)
