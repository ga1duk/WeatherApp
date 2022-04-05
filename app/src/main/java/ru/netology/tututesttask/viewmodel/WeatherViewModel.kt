package ru.netology.tututesttask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.netology.tututesttask.dto.ForecastModel
import ru.netology.tututesttask.repository.WeatherRepository
import ru.netology.tututesttask.repository.WeatherRepositoryImpl

class WeatherViewModel : ViewModel() {
    private val repository: WeatherRepository = WeatherRepositoryImpl()
    private val _data = MutableLiveData<ForecastModel>()
    val data: LiveData<ForecastModel>
        get() = _data

    init {
        loadWeather()
    }

private fun loadWeather() = viewModelScope.launch {
        _data.value = repository.getWeather()
}
}