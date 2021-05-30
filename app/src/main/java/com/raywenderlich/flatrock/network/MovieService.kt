package com.raywenderlich.flatrock.network

import com.raywenderlich.flatrock.BuildConfig
import com.raywenderlich.flatrock.data.result.PaginatedData
import com.raywenderlich.flatrock.data.result.details.MovieDetails
import retrofit2.http.GET
import retrofit2.http.Path


interface MovieService {





    @GET("popular${BuildConfig.API_KEY}")
    suspend fun getPopular(): PaginatedData



    @GET("top_rated${BuildConfig.API_KEY}")
    suspend fun getRated():PaginatedData



    @GET("{id}${BuildConfig.API_KEY}")
    suspend fun getDetails(@Path("id") id: String): MovieDetails





}