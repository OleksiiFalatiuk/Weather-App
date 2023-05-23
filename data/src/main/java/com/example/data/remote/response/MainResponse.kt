package com.example.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MainResponse(
    @SerialName("location")
    var location: LocationResponse,
    @SerialName("current")
    var current: CurrentResponse,
    @SerialName("forecast")
    var forecast: ForecastResponse
)
