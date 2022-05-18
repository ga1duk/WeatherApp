package ru.mypackage.myweatherapp.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import ru.mypackage.myweatherapp.BuildConfig
import ru.mypackage.myweatherapp.dto.ForecastModelRemote

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
   @Headers("X-Yandex-API-Key: 40694b7c-e274-45f8-b4bb-69288f561df1")
   suspend fun getWeather(@Query(LAT) lat: Double, @Query (LON) lon: Double): Response<ForecastModelRemote>
}

object WeatherApi {
    val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}