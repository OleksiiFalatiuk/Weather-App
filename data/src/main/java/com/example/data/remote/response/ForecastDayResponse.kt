package com.example.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDayResponse(
    @SerialName("date_epoch") var dateEpoch: Int,
    @SerialName("day") var day: DayResponse,
)
