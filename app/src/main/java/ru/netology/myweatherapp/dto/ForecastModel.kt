package ru.netology.myweatherapp.dto

import ru.netology.myweatherapp.R

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

    fun getConditionLocalized(): String {
        return when (condition) {
            CLEAR -> R.string.clear_conditions_text.toString()
            PARTLY_CLOUDY -> R.string.partly_cloudy_conditions_text.toString()
            CLOUDY -> R.string.cloudy_conditions_text.toString()
            OVERCAST -> R.string.overcast_conditions_text.toString()
            DRIZZLE -> R.string.drizzle_conditions_text.toString()
            LIGHT_RAIN -> R.string.light_rain_conditions_text.toString()
            RAIN -> R.string.rain_conditions_text.toString()
            MODERATE_RAIN -> R.string.moderate_rain_conditions_text.toString()
            HEAVY_RAIN -> R.string.heavy_rain_conditions_text.toString()
            CONTINUOUS_HEAVY_RAIN -> R.string.continuous_heavy_rain_conditions_text.toString()
            SHOWERS -> R.string.showers_conditions_text.toString()
            WET_SNOW -> R.string.wet_snow_conditions_text.toString()
            LIGHT_SNOW -> R.string.light_snow_conditions_text.toString()
            SNOW -> R.string.snow_conditions_text.toString()
            SNOW_SHOWERS -> R.string.snow_showers_conditions_text.toString()
            HAIL -> R.string.hail_conditions_text.toString()
            THUNDERSTORM -> R.string.thunderstorm_conditions_text.toString()
            THUNDERSTORM_WITH_RAIN -> R.string.thunderstorm_with_rain_conditions_text.toString()
            THUNDERSTORM_WITH_HAIL -> R.string.thunderstorm_with_hail_conditions_text.toString()
            else -> condition
        }
    }

//    fun getConditionLocalized(): String {
//        val condition = ""
//        return when (WeatherConditions.create(condition)) {
//            WeatherConditions.CLEAR -> R.string.clear_conditions_text.toString()
//            WeatherConditions.PARTLY_CLOUDY -> R.string.partly_cloudy_conditions_text.toString()
//            WeatherConditions.CLOUDY -> R.string.cloudy_conditions_text.toString()
//            WeatherConditions.OVERCAST -> R.string.overcast_conditions_text.toString()
//            WeatherConditions.DRIZZLE -> R.string.drizzle_conditions_text.toString()
//            WeatherConditions.LIGHT_RAIN -> R.string.light_rain_conditions_text.toString()
//            WeatherConditions.RAIN -> R.string.rain_conditions_text.toString()
//            WeatherConditions.MODERATE_RAIN -> R.string.moderate_rain_conditions_text.toString()
//            WeatherConditions.HEAVY_RAIN -> R.string.heavy_rain_conditions_text.toString()
//            WeatherConditions.CONTINUOUS_HEAVY_RAIN -> R.string.continuous_heavy_rain_conditions_text.toString()
//            WeatherConditions.SHOWERS -> R.string.showers_conditions_text.toString()
//            WeatherConditions.WET_SNOW -> R.string.wet_snow_conditions_text.toString()
//            WeatherConditions.LIGHT_SNOW -> R.string.light_snow_conditions_text.toString()
//            WeatherConditions.SNOW -> R.string.snow_conditions_text.toString()
//            WeatherConditions.SNOW_SHOWERS -> R.string.snow_showers_conditions_text.toString()
//            WeatherConditions.HAIL -> R.string.hail_conditions_text.toString()
//            WeatherConditions.THUNDERSTORM -> R.string.thunderstorm_conditions_text.toString()
//            WeatherConditions.THUNDERSTORM_WITH_RAIN -> R.string.thunderstorm_with_rain_conditions_text.toString()
//            WeatherConditions.THUNDERSTORM_WITH_HAIL -> R.string.thunderstorm_with_hail_conditions_text.toString()
//            else -> condition
//        }
//    }
}

data class GeoObjectModel(val locality: LocalityModel)

data class LocalityModel(val name: String)
