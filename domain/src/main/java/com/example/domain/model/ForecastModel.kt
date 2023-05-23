package com.example.domain.model

import com.example.domain.type.TypeOfWeather

data class ForecastModel(
    val dateEpoch: Int,
    val avgtempC: Double,
    val typeOfWeather: TypeOfWeather,
    val stateOfWeather: String
)
