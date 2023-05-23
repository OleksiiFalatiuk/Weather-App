package com.example.weatherapp.di

import com.example.data.remote.NetworkModule
import org.koin.dsl.module

val retrofitDi = module {

    factory { NetworkModule().api }

}