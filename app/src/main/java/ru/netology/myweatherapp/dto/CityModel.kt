package ru.netology.myweatherapp.dto

data class CityModel(
    val id: Int,
    val city: String,
    val lat: Double,
    val lon: Double
)
