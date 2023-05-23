package com.example.weatherapp.ui.main.weather_fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.ForecastModel
import com.example.domain.type.TypeOfWeather
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ItemForecastBinding
import com.example.weatherapp.utils.ActivityExt.Companion.getDateMonthLong

class WeatherViewHolder(private val binding: ItemForecastBinding) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(item: ForecastModel) = with(binding) {
        tvDay.text = getDateMonthLong(item.dateEpoch.toLong())
        tvDegree.text = "${item.avgtempC}°C"
        Glide.with(binding.root).load(whichTypeOfWeather(item.typeOfWeather)).into(ivForecast)
    }

    private fun whichTypeOfWeather(type: TypeOfWeather): Int{
        return when(type){
            TypeOfWeather.SUNNY -> R.drawable.item_image_sunny
            TypeOfWeather.CLOUDY -> R.drawable.item_image_sun_with_cloud
            TypeOfWeather.RAINY -> R.drawable.item_image_rainy
            TypeOfWeather.OVERCASTY -> R.drawable.item_image_thunderstorm
        }
    }

    companion object {
        fun create(parent: ViewGroup): WeatherViewHolder {
            val binding = ItemForecastBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return WeatherViewHolder(binding)
        }
    }
}