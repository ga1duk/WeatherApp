package ru.mypackage.myweatherapp.repository

import ru.mypackage.myweatherapp.R
import ru.mypackage.nmedia.error.ApiError
import ru.mypackage.nmedia.error.NetworkError
import ru.mypackage.nmedia.error.UnknownError
import ru.mypackage.myweatherapp.api.WeatherApi
import ru.mypackage.myweatherapp.dto.CityModel
import ru.mypackage.myweatherapp.dto.ForecastModelRemote
import java.io.IOException

class WeatherRepositoryImpl : WeatherRepository {

    override fun loadCities(): List<CityModel> {
        val cities = mutableListOf<CityModel>()
        cities.add(CityModel(1, R.string.city_moscow_text, 55.7522, 37.6156))
        cities.add(CityModel(2, R.string.city_belgorod_text, 50.6008242, 36.5710883))
        cities.add(CityModel(3, R.string.city_ufa_text, 54.7230808, 55.9301263))
        cities.add(CityModel(4, R.string.city_lipetsk_text, 52.6021103, 39.4855761))
        cities.add(CityModel(5, R.string.city_voronezh_text, 51.6784579, 39.1350082))
        cities.add(CityModel(6, R.string.city_tambov_text, 52.7299159, 41.3728648))
        cities.add(CityModel(7, R.string.city_saratov_text, 51.5493048, 45.9210095))
        cities.add(CityModel(8, R.string.city_penza_text, 53.1950709, 44.8905371))
        cities.add(CityModel(9, R.string.city_astrahan_text, 46.3470445, 48.0265852))
        cities.add(CityModel(10, R.string.city_kaliningrad_text, 54.7115288, 20.324447))
        cities.add(CityModel(11, R.string.city_orel_text, 52.9712018, 36.061651))
        cities.add(CityModel(12, R.string.city_kursk_text, 51.7385355, 36.0970554))
        cities.add(CityModel(13, R.string.city_arhangelsk_text, 64.5394959,40.5142958))
        cities.add(CityModel(14, R.string.city_murmansk_text, 68.9706193, 33.0728239))
        cities.add(CityModel(15, R.string.city_adler_text, 43.4392552,39.8676674))
        cities.add(CityModel(16, R.string.city_krasnodar_text, 45.0576759, 38.8575892))

        return cities
    }

    override suspend fun getWeather(lat: Double, lon: Double): ForecastModelRemote {
        try {
            val response = WeatherApi.retrofitService.getWeather(lat, lon)
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }
            val body = response.body() ?: throw ApiError(response.code(), response.message())
            return body
        } catch (e: IOException) {
            throw NetworkError
        } catch (e: Exception) {
            throw UnknownError
        }
    }
}