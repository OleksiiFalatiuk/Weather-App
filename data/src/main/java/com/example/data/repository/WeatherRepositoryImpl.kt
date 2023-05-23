package com.example.data.repository

import com.example.data.remote.NetworkApiService
import com.example.data.remote.response.MainResponse
import com.example.data.repository.WeatherRepositoryImpl.ImageFactory.createImage
import com.example.domain.model.CurrentModel
import com.example.domain.model.ForecastModel
import com.example.domain.model.LocationModel
import com.example.domain.model.MainWeatherModel
import com.example.domain.repository.WeatherRepository
import com.example.domain.type.TypeOfWeather

class WeatherRepositoryImpl(
    private val api: NetworkApiService
) : WeatherRepository {
    override suspend fun getMainDataOfWeather(coordinates: String): Result<MainWeatherModel> {
        val result = runCatching {
            api.getForecast(
                q = coordinates
            )
        }
        result.fold(
            onSuccess = {
                return Result.success(mapMainDataOfWeather(it))
            },
            onFailure = {
                return Result.failure(it)
            }
        )
    }

    private fun mapMainDataOfWeather(data: MainResponse): MainWeatherModel {
        return MainWeatherModel(
            location = LocationModel(
                name = data.location.name,
                region = data.location.region,
                country = data.location.country,
                localtimeEpoch = data.location.localtimeEpoch,
            ),
            current = CurrentModel(
                tempC = data.current.tempC,
                isDay = data.current.isDay,
                typeOfWeather = createImage(data.current.condition.code),
                stateOfWeather = data.current.condition.stateOfWeather
            ),
            forecast = data.forecast.forecastday.map {
                ForecastModel(
                    dateEpoch = it.dateEpoch,
                    avgtempC = it.day.avgtempC,
                    typeOfWeather = createImage(it.day.condition.code),
                    stateOfWeather = data.current.condition.stateOfWeather
                )
            }
        )
    }

    object ImageFactory {

        private val listSunny = listOf(1000)
        private val listRainy = listOf(1063, 1087, 1117, 1072, 1150, 1153, 1168, 1171, 1180, 1183, 1186, 1189, 1192, 1195, 1198, 1201, 1240, 1243, 1246, 1273, 1276)
        private val listCloudy = listOf(1003, 1006, 1009, 1030)
        private val listOvercasty = listOf(1282, 1279, 1276, 1273, 1087, 1066, 1114, 1210, 1213, 1216, 1219, 1222, 1225, 1255, 1258)

        fun createImage(code: Int): TypeOfWeather {
            return when{
                listSunny.contains(code) -> TypeOfWeather.SUNNY
                listCloudy.contains(code) -> TypeOfWeather.CLOUDY
                listRainy.contains(code) -> TypeOfWeather.RAINY
                listOvercasty.contains(code) -> TypeOfWeather.OVERCASTY
                else -> TypeOfWeather.SUNNY
            }
        }

    }

}