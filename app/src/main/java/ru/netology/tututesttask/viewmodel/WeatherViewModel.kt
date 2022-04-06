package ru.netology.tututesttask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.netology.tututesttask.dto.CityModel
import ru.netology.tututesttask.dto.ForecastModel
import ru.netology.tututesttask.model.WeatherModelState
import ru.netology.tututesttask.repository.WeatherRepository
import ru.netology.tututesttask.repository.WeatherRepositoryImpl

class WeatherViewModel : ViewModel() {

    private val repository: WeatherRepository = WeatherRepositoryImpl()
    private val _cityData = MutableLiveData<List<CityModel>>()
    val cityData: LiveData<List<CityModel>>
        get() = _cityData
    private val _weatherData = MutableLiveData<ForecastModel>()
    val weatherData: LiveData<ForecastModel>
        get() = _weatherData
    private val _weatherDataState = MutableLiveData<WeatherModelState>()
    val weatherDataState: LiveData<WeatherModelState>
        get() = _weatherDataState

    init {
        loadCities()
    }

    fun loadCities() {
        _cityData.value = repository.loadCities()
    }

    fun loadWeather(lat: Double, lon: Double) = viewModelScope.launch {
        try {
            _weatherDataState.value = WeatherModelState(loading = true)
            _weatherData.value = repository.getWeather(lat, lon)
            _weatherDataState.value = WeatherModelState(forecast = true)
        } catch (e: Exception) {
            _weatherDataState.value = WeatherModelState(error = true)
        }
    }

    fun refreshWeather(lat: Double, lon: Double) = viewModelScope.launch {
        try {
            _weatherDataState.value = WeatherModelState(refreshing = true)
            _weatherData.value = repository.getWeather(lat, lon)
            _weatherDataState.value = WeatherModelState(forecast = true)
        } catch (e: Exception) {
            _weatherDataState.value = WeatherModelState(error = true)
        }
    }
}