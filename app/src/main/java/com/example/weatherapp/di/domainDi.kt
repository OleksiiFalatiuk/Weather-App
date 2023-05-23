package com.example.weatherapp.di

import com.example.domain.use_case.GetMainDataOfWeatherUseCase
import com.example.domain.use_case.GetMainDataOfWeatherUseCaseImpl
import org.koin.dsl.module

val domainDi = module {
    factory<GetMainDataOfWeatherUseCase>{
        GetMainDataOfWeatherUseCaseImpl(
            repository = get()
        )
    }
}