package com.raywenderlich.flatrock.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Network {


    val movieService by lazy { createMovieService() }




    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }




    private fun createMovieService():MovieService{
        val retrofitBuilder = Retrofit.Builder()
        retrofitBuilder.baseUrl("https://api.themoviedb.org/3/movie/")
        retrofitBuilder.client(
            OkHttpClient().newBuilder()
                .addInterceptor(loggingInterceptor)
                .build()
        )
        retrofitBuilder.addConverterFactory(mochiConvertor())
        return retrofitBuilder.build().create(MovieService::class.java)
    }






    private fun mochiConvertor() =
        MoshiConverterFactory.create(
            Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()
        )


}