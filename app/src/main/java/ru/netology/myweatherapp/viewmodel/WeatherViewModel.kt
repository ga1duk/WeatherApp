package ru.netology.myweatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.netology.myweatherapp.WeatherMapper
import ru.netology.myweatherapp.dto.CityModel
import ru.netology.myweatherapp.model.ForecastModelLocal
import ru.netology.myweatherapp.model.WeatherModelState
import ru.netology.myweatherapp.repository.WeatherRepository
import ru.netology.myweatherapp.repository.WeatherRepositoryImpl

class WeatherViewModel : ViewModel() {

    private val repository: WeatherRepository = WeatherRepositoryImpl()
    private val mapper: WeatherMapper = WeatherMapper()
    private val _cityData = MutableLiveData<List<CityModel>>()
    val cityData: LiveData<List<CityModel>>
        get() = _cityData
    private val _weatherData = MutableLiveData<ForecastModelLocal>()
    val weatherData: LiveData<ForecastModelLocal>
        get() = _weatherData
    private val _weatherDataState = MutableLiveData<WeatherModelState>()
    val weatherDataState: LiveData<WeatherModelState>
        get() = _weatherDataState

    init {
        loadCities()
    }

    private fun loadCities() {
        _cityData.value = repository.loadCities()
    }

    fun loadWeather(lat: Double, lon: Double) = viewModelScope.launch {
        try {
            _weatherDataState.value = WeatherModelState(loading = true)
            _weatherData.value = mapper.mapForecast(repository.getWeather(lat, lon))
            _weatherDataState.value = WeatherModelState(forecast = true)
        } catch (e: Exception) {
            _weatherDataState.value = WeatherModelState(error = true)
        }
    }

    fun refreshWeather(lat: Double, lon: Double) = viewModelScope.launch {
        try {
            _weatherDataState.value = WeatherModelState(refreshing = true)
            _weatherData.value = mapper.mapForecast(repository.getWeather(lat, lon))
            _weatherDataState.value = WeatherModelState(forecast = true)
        } catch (e: Exception) {
            _weatherDataState.value = WeatherModelState(error = true)
        }
    }
}