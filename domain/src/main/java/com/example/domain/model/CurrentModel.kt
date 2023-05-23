package com.example.domain.model

import com.example.domain.type.TypeOfWeather

data class CurrentModel(
    val tempC: Double = 0.0,
    val isDay: Int = 0,
    val typeOfWeather: TypeOfWeather = TypeOfWeather.SUNNY,
    val stateOfWeather: String = ""
)
