package com.example.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DayResponse(
    @SerialName("avgtemp_c") var avgtempC: Double,
    @SerialName("condition") var condition: ConditionResponse,
)
