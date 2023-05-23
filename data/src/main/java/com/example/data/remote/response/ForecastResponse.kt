package com.example.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastResponse(
    @SerialName("forecastday")
    var forecastday: List<ForecastDayResponse>
)
