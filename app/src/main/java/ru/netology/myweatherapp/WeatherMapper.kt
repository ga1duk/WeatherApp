package ru.netology.myweatherapp

import ru.netology.myweatherapp.dto.ForecastModel

const val CLEAR = "clear"
const val PARTLY_CLOUDY = "partly-cloudy"
const val CLOUDY = "cloudy"
const val OVERCAST = "overcast"
const val DRIZZLE = "drizzle"
const val LIGHT_RAIN = "light-rain"
const val RAIN = "rain"
const val MODERATE_RAIN = "moderate-rain"
const val HEAVY_RAIN = "heavy-rain"
const val CONTINUOUS_HEAVY_RAIN = "continuous-heavy-rain"
const val SHOWERS = "showers"
const val WET_SNOW = "wet-snow"
const val LIGHT_SNOW = "light-snow"
const val SNOW = "snow"
const val SNOW_SHOWERS = "snow-showers"
const val HAIL = "hail"
const val THUNDERSTORM = "thunderstorm"
const val THUNDERSTORM_WITH_RAIN = "thunderstorm-with-rain"
const val THUNDERSTORM_WITH_HAIL = "thunderstorm-with-hail"

class WeatherMapper {
    fun mapForecast(input: ForecastModel): ru.netology.myweatherapp.model.ForecastModel {
        val city = input.geo_object?.locality?.name ?: ""
        val temp = input.fact?.temp.toString()
        val icon = input.fact?.icon?.let {
            "https://yastatic.net/weather/i/icons/funky/dark/$it.svg"
        }
        val condition = input.fact?.condition?.let {
            when (it) {
                CLEAR -> R.string.clear_conditions_text
                PARTLY_CLOUDY -> R.string.partly_cloudy_conditions_text
                CLOUDY -> R.string.cloudy_conditions_text
                OVERCAST -> R.string.overcast_conditions_text
                DRIZZLE -> R.string.drizzle_conditions_text
                LIGHT_RAIN -> R.string.light_rain_conditions_text
                RAIN -> R.string.rain_conditions_text
                MODERATE_RAIN -> R.string.moderate_rain_conditions_text
                HEAVY_RAIN -> R.string.heavy_rain_conditions_text
                CONTINUOUS_HEAVY_RAIN -> R.string.continuous_heavy_rain_conditions_text
                SHOWERS -> R.string.showers_conditions_text
                WET_SNOW -> R.string.wet_snow_conditions_text
                LIGHT_SNOW -> R.string.light_snow_conditions_text
                SNOW -> R.string.snow_conditions_text
                SNOW_SHOWERS -> R.string.snow_showers_conditions_text
                HAIL -> R.string.hail_conditions_text
                THUNDERSTORM -> R.string.thunderstorm_conditions_text
                THUNDERSTORM_WITH_RAIN -> R.string.thunderstorm_with_rain_conditions_text
                THUNDERSTORM_WITH_HAIL -> R.string.thunderstorm_with_hail_conditions_text
                else -> 0
            }
        } ?: 0
        return ru.netology.myweatherapp.model.ForecastModel(city, temp, icon, condition)
    }
}