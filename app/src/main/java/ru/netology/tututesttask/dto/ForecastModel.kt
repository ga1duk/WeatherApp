package ru.netology.tututesttask.dto

data class ForecastModel(val fact: FactModel, val geo_object: GeoObject?)

data class FactModel(val temp: Int)

data class GeoObject(val locality: Locality)

data class Locality(val name: String)
