package ru.netology.myweatherapp.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import ru.netology.myweatherapp.BuildConfig
import ru.netology.myweatherapp.dto.ForecastModelRemote

private const val BASE_URL = "${BuildConfig.BASE_URL}/v2/"
private const val LAT = "lat"
private const val LON = "lon"

val logging = HttpLoggingInterceptor().apply {
    if (BuildConfig.DEBUG) {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

private val okhttp = OkHttpClient.Builder()
    .addInterceptor(logging)
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .client(okhttp)
    .baseUrl(BASE_URL)
    .build()

interface WeatherApiService {
   @GET("forecast")
   @Headers("X-Yandex-API-Key: 573dbee2-4c5e-47f4-9abb-e465c1d993bd")
   suspend fun getWeather(@Query(LAT) lat: Double, @Query (LON) lon: Double): Response<ForecastModelRemote>
}

object WeatherApi {
    val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}