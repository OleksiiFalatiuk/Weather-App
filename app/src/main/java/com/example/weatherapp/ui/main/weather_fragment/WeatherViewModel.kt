package com.example.weatherapp.ui.main.weather_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.MainWeatherModel
import com.example.domain.use_case.GetMainDataOfWeatherUseCase
import com.example.weatherapp.utils.Resource
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getMainDataOfWeatherUseCase: GetMainDataOfWeatherUseCase
): ViewModel() {

    private val _loadDataOfWeather = MutableLiveData<Resource<MainWeatherModel>>()
    val loadDataOfWeather: LiveData<Resource<MainWeatherModel>> = _loadDataOfWeather

    fun loadWeatherData(coordinates: String){
        viewModelScope.launch {
            _loadDataOfWeather.value = Resource.loading()
            val result = getMainDataOfWeatherUseCase.getMainDataOfWeather(coordinates)
            result.fold(
                onSuccess = {
                    _loadDataOfWeather.value = Resource.success(it)
                },
                onFailure = {
                    _loadDataOfWeather.value = Resource.error(it)
                }
            )
        }
    }

}