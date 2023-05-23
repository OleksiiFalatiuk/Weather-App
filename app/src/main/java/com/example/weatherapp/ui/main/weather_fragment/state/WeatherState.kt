package com.example.weatherapp.ui.main.weather_fragment.state

import com.example.domain.model.CurrentModel
import com.example.domain.model.ForecastModel
import com.example.domain.model.LocationModel
import com.example.weatherapp.utils.Resource

data class WeatherState(
    val location: LocationModel = LocationModel(),
    val current: CurrentModel= CurrentModel(),
    val forecast: List<ForecastModel> = emptyList(),
    val colorOfRoot: String = "",
    val colorOfMainElement: Int = 0,
    val resource: Resource.Status = Resource.Status.LOADING,
    val imageOfMain: Int = 0
)
