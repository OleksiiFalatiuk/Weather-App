package com.example.weatherapp.di

import com.example.data.repository.WeatherRepositoryImpl
import com.example.domain.repository.WeatherRepository
import org.koin.dsl.module

val dataDi = module {
    single<WeatherRepository>{
        WeatherRepositoryImpl(
            api = get()
        )
    }
}