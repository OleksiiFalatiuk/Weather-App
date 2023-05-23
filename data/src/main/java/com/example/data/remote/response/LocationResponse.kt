package com.example.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(
    @SerialName("name")
    var name: String,
    @SerialName("region")
    var region: String,
    @SerialName("country")
    var country: String,
    @SerialName("localtime_epoch")
    var localtimeEpoch: Int,
)
