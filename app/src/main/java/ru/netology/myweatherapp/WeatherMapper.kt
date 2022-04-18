package ru.netology.myweatherapp

import ru.netology.myweatherapp.dto.ForecastModelRemote
import ru.netology.myweatherapp.model.ForecastModelLocal

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

const val MOSCOW = "Москва"
const val BELGOROD = "Белгород"
const val UFA = "Уфа"
const val LIPETSK = "Липецк"
const val VORONEZH = "Воронеж"
const val TAMBOV = "Тамбов"
const val SARATOV = "Саратов"
const val PENZA = "Пенза"
const val ASTRAHAN = "Астрахань"
const val OREL = "Орёл"
const val KURSK = "Курск"
const val ARHANGELSK = "Архангельск"
const val MURMANSK = "Мурманск"
const val ADLER = "Адлер"
const val KRASNODAR = "Краснодар"


class WeatherMapper {
    fun mapForecast(input: ForecastModelRemote): ForecastModelLocal {
        val city = input.geo_object?.locality?.name?.let {
            when (it) {
                MOSCOW -> R.string.city_moscow_text
                BELGOROD -> R.string.city_belgorod_text
                UFA -> R.string.city_ufa_text
                LIPETSK -> R.string.city_lipetsk_text
                VORONEZH -> R.string.city_voronezh_text
                TAMBOV -> R.string.city_tambov_text
                SARATOV -> R.string.city_saratov_text
                PENZA -> R.string.city_penza_text
                ASTRAHAN -> R.string.city_astrahan_text
                OREL -> R.string.city_orel_text
                KURSK -> R.string.city_kursk_text
                ARHANGELSK -> R.string.city_arhangelsk_text
                MURMANSK -> R.string.city_murmansk_text
                ADLER -> R.string.city_adler_text
                KRASNODAR -> R.string.city_krasnodar_text
                else -> 0
            }
        } ?: 0
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
        return ForecastModelLocal(city, temp, icon, condition)
    }
}