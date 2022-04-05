package ru.netology.tututesttask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.netology.tututesttask.dto.CityModel
import ru.netology.tututesttask.dto.ForecastModel
import ru.netology.tututesttask.repository.WeatherRepository
import ru.netology.tututesttask.repository.WeatherRepositoryImpl

class WeatherViewModel : ViewModel() {

    private val repository: WeatherRepository = WeatherRepositoryImpl()
    private val _data = MutableLiveData<List<CityModel>>()
    val data: LiveData<List<CityModel>>
        get() = _data

    private val _weatherData = MutableLiveData<ForecastModel>()
    val weatherData: LiveData<ForecastModel>
        get() = _weatherData

    init {
        loadCities()
    }

    fun loadCities() {
        _data.value = repository.loadCities()
    }

    fun loadWeather(lat: Double, lon: Double) = viewModelScope.launch {
        _weatherData.value = repository.getWeather(lat, lon)
    }
}