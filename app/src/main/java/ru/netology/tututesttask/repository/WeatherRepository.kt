package ru.netology.tututesttask.repository

import ru.netology.tututesttask.dto.Forecast

interface WeatherRepository {
    suspend fun getWeather(): Forecast
}