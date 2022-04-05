package ru.netology.tututesttask.repository

import ru.netology.tututesttask.dto.CityModel

interface CityRepository {
    fun loadCities(): List<CityModel>
}