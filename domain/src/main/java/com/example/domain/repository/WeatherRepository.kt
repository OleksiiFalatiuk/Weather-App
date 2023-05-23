package com.example.domain.repository

import com.example.domain.model.MainWeatherModel

interface WeatherRepository {
    suspend fun getMainDataOfWeather(coordinates: String): Result<MainWeatherModel>
}