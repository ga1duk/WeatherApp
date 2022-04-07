package ru.netology.myweatherapp.model

data class WeatherModelState(
    val loading: Boolean = false,
    val error: Boolean = false,
    val refreshing: Boolean = false,
    val forecast: Boolean = false
)