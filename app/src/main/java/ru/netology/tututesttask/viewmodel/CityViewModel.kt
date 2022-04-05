package ru.netology.tututesttask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.netology.tututesttask.dto.CityModel
import ru.netology.tututesttask.dto.ForecastModel
import ru.netology.tututesttask.repository.CityRepository
import ru.netology.tututesttask.repository.CityRepositoryImpl
import ru.netology.tututesttask.repository.WeatherRepository
import ru.netology.tututesttask.repository.WeatherRepositoryImpl

class CityViewModel : ViewModel() {
    private val repository: CityRepository = CityRepositoryImpl()
    private val _data = MutableLiveData<List<CityModel>>()
    val data: LiveData<List<CityModel>>
        get() = _data

    init{
        loadCities()
    }

    private fun loadCities() {
        _data.value = repository.loadCities()
    }
}