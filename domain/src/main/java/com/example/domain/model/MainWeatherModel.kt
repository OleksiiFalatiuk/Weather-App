package com.example.domain.model

data class MainWeatherModel(
    val location: LocationModel,
    val current: CurrentModel,
    val forecast: List<ForecastModel>
)
