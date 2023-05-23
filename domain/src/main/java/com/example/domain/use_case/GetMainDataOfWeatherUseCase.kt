package com.example.domain.use_case

import com.example.domain.model.MainWeatherModel
import com.example.domain.repository.WeatherRepository

interface GetMainDataOfWeatherUseCase {
    suspend fun getMainDataOfWeather(coordinates: String): Result<MainWeatherModel>
}

class GetMainDataOfWeatherUseCaseImpl(
    private val repository: WeatherRepository
):GetMainDataOfWeatherUseCase{
    override suspend fun getMainDataOfWeather(coordinates: String): Result<MainWeatherModel> =
        repository.getMainDataOfWeather(coordinates)

}