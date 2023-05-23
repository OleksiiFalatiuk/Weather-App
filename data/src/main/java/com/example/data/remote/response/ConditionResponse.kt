package com.example.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConditionResponse(
    @SerialName("code") var code: Int,
    @SerialName("text") var stateOfWeather: String
)
