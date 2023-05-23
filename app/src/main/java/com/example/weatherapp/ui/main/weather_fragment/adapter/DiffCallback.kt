package com.example.weatherapp.ui.main.weather_fragment.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.model.ForecastModel

object DiffCallback : DiffUtil.ItemCallback<ForecastModel>() {
    override fun areItemsTheSame(oldItem: ForecastModel, newItem: ForecastModel): Boolean {
        return oldItem.dateEpoch == newItem.dateEpoch
    }

    override fun areContentsTheSame(
        oldItem: ForecastModel,
        newItem: ForecastModel
    ): Boolean {
        return oldItem == newItem
    }
}