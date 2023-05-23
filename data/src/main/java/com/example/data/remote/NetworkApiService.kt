package com.example.data.remote

import com.example.data.remote.response.MainResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NetworkApiService {

    @GET("forecast.json")
    suspend fun getForecast(
        @Header("key") key: String = "c7435d562c4a40c2b3e110436232205",
        @Query("q") q: String,
        @Query("days") days: Int = 7
    ): MainResponse

}