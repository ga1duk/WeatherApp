package ru.netology.tututesttask.repository

import ru.netology.tututesttask.dto.ForecastModel

interface WeatherRepository {
    suspend fun getWeather(): ForecastModel
}