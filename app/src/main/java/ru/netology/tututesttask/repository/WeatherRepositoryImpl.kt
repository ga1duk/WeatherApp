package ru.netology.tututesttask.repository

import ru.netology.nmedia.error.ApiError
import ru.netology.nmedia.error.NetworkError
import ru.netology.nmedia.error.UnknownError
import ru.netology.tututesttask.api.WeatherApi
import ru.netology.tututesttask.dto.CityModel
import ru.netology.tututesttask.dto.ForecastModel
import java.io.IOException

class WeatherRepositoryImpl : WeatherRepository {

    override fun loadCities(): List<CityModel> {
        val cities = mutableListOf<CityModel>()
        cities.add(CityModel(1, "Москва", 55.7522, 37.6156))
        cities.add(CityModel(2, "Белгород", 50.6008242, 36.5710883))
        cities.add(CityModel(3, "Уфа", 54.7230808, 55.9301263))
        cities.add(CityModel(4, "Липецк", 52.6021103, 39.4855761))
        cities.add(CityModel(5, "Воронеж", 51.6784579, 39.1350082))
        cities.add(CityModel(6, "Тамбов", 52.7299159, 41.3728648))
        cities.add(CityModel(7, "Саратов", 51.5493048, 45.9210095))
        cities.add(CityModel(8, "Пенза", 53.1950709, 44.8905371))
        cities.add(CityModel(9, "Астрахань", 46.3470445, 48.0265852))
        cities.add(CityModel(10, "Калининград", 54.7115288, 20.324447))
        cities.add(CityModel(11, "Орёл", 52.9712018, 36.061651))
        cities.add(CityModel(12, "Курск", 51.7385355, 36.0970554))
        cities.add(CityModel(13, "Архангельск", 64.5394959,40.5142958))
        cities.add(CityModel(14, "Мурманск", 68.9706193, 33.0728239))
        cities.add(CityModel(15, "Адлер", 43.4392552,39.8676674))
        cities.add(CityModel(16, "Краснодар", 45.0576759, 38.8575892))

        return cities
    }

    override suspend fun getWeather(lat: Double, lon: Double): ForecastModel {
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