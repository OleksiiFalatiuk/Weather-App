package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.di.dataDi
import com.example.weatherapp.di.domainDi
import com.example.weatherapp.di.retrofitDi
import com.example.weatherapp.di.uiDi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(dataDi, domainDi, uiDi, retrofitDi))
        }
    }

}