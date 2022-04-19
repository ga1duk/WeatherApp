package ru.mypackage.myweatherapp.dto

data class ForecastModelRemote(
    val fact: FactModel?,
    val geo_object: GeoObjectModel?
)

data class FactModel(
    val temp: Int,
    val icon: String,
    val condition: String
)

data class GeoObjectModel(val locality: LocalityModel)

data class LocalityModel(val name: String)
