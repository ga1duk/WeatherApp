package ru.netology.myweatherapp.repository

import ru.netology.myweatherapp.dto.CityModel
import ru.netology.myweatherapp.dto.ForecastModel

interface WeatherRepository {
    fun loadCities(): List<CityModel>

    suspend fun getWeather(lat: Double, lon: Double): ForecastModel
}