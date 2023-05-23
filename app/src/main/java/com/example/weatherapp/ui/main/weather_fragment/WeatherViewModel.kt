package com.example.weatherapp.ui.main.weather_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.type.TypeOfWeather
import com.example.domain.use_case.GetMainDataOfWeatherUseCase
import com.example.weatherapp.R
import com.example.weatherapp.ui.main.weather_fragment.state.WeatherState
import com.example.weatherapp.utils.Resource
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getMainDataOfWeatherUseCase: GetMainDataOfWeatherUseCase
): ViewModel() {

    private val _loadDataOfWeather = MutableLiveData<WeatherState>()
    val loadDataOfWeather: LiveData<WeatherState> = _loadDataOfWeather

    init {
        _loadDataOfWeather.value = WeatherState()
    }

    // here we make request for data with ui states, thanks to this our ui not overloaded, all hard works we do in viewModel
    fun loadWeatherData(coordinates: String){
        viewModelScope.launch {
            val result = getMainDataOfWeatherUseCase.getMainDataOfWeather(coordinates)
            result.fold(
                onSuccess = {
                    val isNight = it.current.isDay != 1
                    _loadDataOfWeather.value = loadDataOfWeather.value?.copy(
                        location = it.location,
                        current = it.current,
                        forecast = it.forecast.apply {
                            it.forecast.forEach { it1 ->
                                it1.image = whichTypeOfWeather(it1.typeOfWeather)
                                it1.backgroundOfItem = if (isNight) R.drawable.background_for_recycler_items_night else whichDataBackOfItem(it.current.typeOfWeather)
                            }
                        },
                        colorOfRoot = if (isNight) "#E5E7F7" else "#E8FCFF",
                        colorOfMainElement = if (isNight) R.drawable.background_main_weather_night else whichDataToSet(it.current.typeOfWeather),
                        resource = Resource.Status.SUCCESS,
                        imageOfMain = if (isNight) R.drawable.ic_clear else whichDataImage(it.current.typeOfWeather)
                    )
                },
                onFailure = {
                    _loadDataOfWeather.value = loadDataOfWeather.value?.copy(
                        resource = Resource.Status.ERROR
                    )
                }
            )
        }
    }

    private fun whichDataToSet(type: TypeOfWeather): Int{
        return when(type){
            TypeOfWeather.SUNNY -> R.drawable.background_main_weather_sunny
            TypeOfWeather.CLOUDY -> R.drawable.background_main_weather_cloudy
            TypeOfWeather.RAINY -> R.drawable.background_main_weather_snowy
            TypeOfWeather.OVERCASTY -> R.drawable.background_main_weather_sunny
        }
    }

    private fun whichDataImage(type: TypeOfWeather): Int{
        return when(type){
            TypeOfWeather.SUNNY -> R.drawable.ic_sunny
            TypeOfWeather.CLOUDY -> R.drawable.ic_cloudy
            TypeOfWeather.RAINY -> R.drawable.ic_snowy
            TypeOfWeather.OVERCASTY -> R.drawable.ic_snowy
        }
    }

    private fun whichDataBackOfItem(type: TypeOfWeather): Int{
        return when(type){
            TypeOfWeather.SUNNY -> R.drawable.background_for_recycler_items_sunny
            TypeOfWeather.CLOUDY -> R.drawable.background_for_recycler_items_cloudy
            TypeOfWeather.RAINY -> R.drawable.background_for_recycler_items_snowy
            TypeOfWeather.OVERCASTY -> R.drawable.background_for_recycler_items_snowy
        }
    }

    private fun whichTypeOfWeather(type: TypeOfWeather): Int{
        return when(type){
            TypeOfWeather.SUNNY -> R.drawable.item_image_sunny
            TypeOfWeather.CLOUDY -> R.drawable.item_image_sun_with_cloud
            TypeOfWeather.RAINY -> R.drawable.item_image_rainy
            TypeOfWeather.OVERCASTY -> R.drawable.item_image_thunderstorm
        }
    }

}