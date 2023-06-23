package com.example.workoutapp.api

import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("current.json")
    suspend fun getWeather(@Query("key") key: String, @Query("q") q: String): Response<Weather>
}