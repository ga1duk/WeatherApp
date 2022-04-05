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
        cities.add(CityModel(1, "Москва", 55.580748, 36.8251127))
        cities.add(CityModel(2, "Белгород", 50.5894186, 36.4330507))
        cities.add(CityModel(3, "Уфа", 54.7406256, 55.7409353))
        cities.add(CityModel(4, "Липецк", 52.6021103, 39.4855761))
        cities.add(CityModel(5, "Воронеж", 51.6995033, 39.0529792))
        cities.add(CityModel(6, "Тамбов", 52.7299159, 41.3728648))
        cities.add(CityModel(7, "Саратов", 51.5314961, 45.8568198))
        cities.add(CityModel(8, "Пенза", 53.1950709, 44.8905371))
        cities.add(CityModel(9, "Астрахань", 46.3653455, 47.8917024))
        cities.add(CityModel(10, "Калининград", 54.7115288, 20.324447))
        cities.add(CityModel(11, "Орёл", 52.9743302, 35.9376521))
        cities.add(CityModel(12, "Курск", 51.7119242, 36.0420488))
        cities.add(CityModel(13, "Архангельск", 64.5609278, 40.2758228))
        cities.add(CityModel(14, "Мурманск", 68.9638735, 32.8059444))
        cities.add(CityModel(15, "Сочи", 43.6017001, 39.6550888))

        return cities
    }

    override suspend fun getWeather(): ForecastModel {
        try {
            val response = WeatherApi.retrofitService.getWeather(55.580748, 36.8251127)
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