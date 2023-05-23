package com.example.domain.model

import com.example.domain.type.TypeOfWeather

data class CurrentModel(
    val tempC: Double,
    val isDay: Int,
    val typeOfWeather: TypeOfWeather,
    val stateOfWeather: String
)
