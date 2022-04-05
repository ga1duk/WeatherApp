package ru.netology.tututesttask.repository

import ru.netology.tututesttask.dto.CityModel
import ru.netology.tututesttask.dto.ForecastModel

interface WeatherRepository {
    fun loadCities(): List<CityModel>

    suspend fun getWeather(): ForecastModel
}