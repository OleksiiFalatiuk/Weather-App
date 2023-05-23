package com.example.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentResponse(
    @SerialName("temp_c") var tempC: Double,
    @SerialName("is_day") var isDay: Int,
    @SerialName("condition") var condition: ConditionResponse,
)
