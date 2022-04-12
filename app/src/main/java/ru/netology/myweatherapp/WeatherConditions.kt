package ru.netology.myweatherapp

enum class WeatherConditions(val condition: String) {
    CLEAR("clear"),
    PARTLY_CLOUDY("partly-cloudy"),
    CLOUDY("cloudy"),
    OVERCAST("overcast"),
    DRIZZLE("drizzle"),
    LIGHT_RAIN("light-rain"),
    RAIN("rain"),
    MODERATE_RAIN("moderate-rain"),
    HEAVY_RAIN("heavy-rain"),
    CONTINUOUS_HEAVY_RAIN("continuous-heavy-rain"),
    SHOWERS("showers"),
    WET_SNOW("wet-snow"),
    LIGHT_SNOW("light-snow"),
    SNOW("snow"),
    SNOW_SHOWERS("snow-showers"),
    HAIL("hail"),
    THUNDERSTORM("thunderstorm"),
    THUNDERSTORM_WITH_RAIN("thunderstorm-with-rain"),
    THUNDERSTORM_WITH_HAIL("thunderstorm-with-hail");

    companion object {
        fun create(condition: String): WeatherConditions {
            return when (condition) {
                "clear" -> CLEAR
                "partly-cloudy" -> PARTLY_CLOUDY
                "cloudy" -> CLOUDY
                "overcast" -> OVERCAST
                "drizzle" -> DRIZZLE
                "light-rain" -> LIGHT_RAIN
                "rain" -> RAIN
                "moderate-rain" -> MODERATE_RAIN
                "heavy-rain" -> HEAVY_RAIN
                "continuous-heavy-rain"-> CONTINUOUS_HEAVY_RAIN
                "showers" -> SHOWERS
                "wet-snow" -> WET_SNOW
                "light-snow" -> LIGHT_SNOW
                "snow" -> SNOW
                "snow-showers" -> SNOW_SHOWERS
                "hail" -> HAIL
                "thunderstorm" -> THUNDERSTORM
                "thunderstorm-with-rain" -> THUNDERSTORM_WITH_RAIN
                "thunderstorm-with-hail" -> THUNDERSTORM_WITH_HAIL
                else -> throw IllegalStateException()
            }
        }
    }
}
