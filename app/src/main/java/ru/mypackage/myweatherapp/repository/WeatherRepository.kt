package ru.mypackage.myweatherapp.repository

import ru.mypackage.myweatherapp.dto.CityModel
import ru.mypackage.myweatherapp.dto.ForecastModelRemote

interface WeatherRepository {
    fun loadCities(): List<CityModel>

    suspend fun getWeather(lat: Double, lon: Double): ForecastModelRemote
}