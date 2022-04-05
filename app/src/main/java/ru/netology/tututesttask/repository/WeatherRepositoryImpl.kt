package ru.netology.tututesttask.repository

import ru.netology.nmedia.error.ApiError
import ru.netology.nmedia.error.NetworkError
import ru.netology.nmedia.error.UnknownError
import ru.netology.tututesttask.api.WeatherApi
import ru.netology.tututesttask.dto.ForecastModel
import java.io.IOException

class WeatherRepositoryImpl : WeatherRepository {

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