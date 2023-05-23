package com.example.weatherapp.di

import com.example.weatherapp.ui.main.weather_fragment.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiDi = module {
    viewModel {
        WeatherViewModel(
            getMainDataOfWeatherUseCase = get()
        )
    }
}