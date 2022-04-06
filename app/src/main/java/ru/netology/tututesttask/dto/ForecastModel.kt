package ru.netology.tututesttask.dto

data class ForecastModel(
    val fact: FactModel?,
    val geo_object: GeoObjectModel?
)

data class FactModel(
    val temp: Int,
    val icon: String,
    val condition: String
) {
    fun getConditionIcon(): String {
        return "https://yastatic.net/weather/i/icons/funky/dark/$icon.svg"
    }
}

data class GeoObjectModel(val locality: LocalityModel)

data class LocalityModel(val name: String)
