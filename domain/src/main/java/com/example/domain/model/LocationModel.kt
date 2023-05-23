package com.example.domain.model

data class LocationModel(
    val name: String = "",
    val region: String = "",
    val country: String = "",
    val localtimeEpoch: Int = 0,
)