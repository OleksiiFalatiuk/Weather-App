package com.example.weatherapp.ui.main.weather_fragment.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.model.ForecastModel

class WeatherAdapter
    : ListAdapter<ForecastModel, WeatherViewHolder>(DiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

}