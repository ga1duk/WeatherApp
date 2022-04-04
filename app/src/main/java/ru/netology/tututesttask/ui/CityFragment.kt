package ru.netology.tututesttask.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.netology.tututesttask.R
import ru.netology.tututesttask.adapter.CityAdapter
import ru.netology.tututesttask.adapter.OnClickListener
import ru.netology.tututesttask.databinding.FragmentCityBinding
import ru.netology.tututesttask.dto.CityModel
import ru.netology.tututesttask.util.StringArg

class CityFragment : Fragment() {

    companion object {
        var Bundle.textArg: String? by StringArg
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCityBinding.inflate(inflater, container, false)

        val cities = mutableListOf<CityModel>()
        cities.add(CityModel("Москва"))
        cities.add(CityModel("Белгород"))
        cities.add(CityModel("Уфа"))
        cities.add(CityModel("Липецк"))
        cities.add(CityModel("Воронеж"))
        cities.add(CityModel("Тамбов"))
        cities.add(CityModel("Саратов"))
        cities.add(CityModel("Пенза"))
        cities.add(CityModel("Астрахань"))
        cities.add(CityModel("Калининград"))
        cities.add(CityModel("Орёл"))
        cities.add(CityModel("Курск"))
        cities.add(CityModel("Архангельск"))
        cities.add(CityModel("Мурманск"))
        cities.add(CityModel("Сочи"))

        val adapter = CityAdapter(object: OnClickListener {
            override fun onNextScreenOpen(city: String) {
                findNavController().navigate(R.id.action_cityFragment_to_weatherFragment,
                    Bundle().apply {
                        textArg = city
                    })
            }
        })
        binding.rvCity.adapter = adapter
        adapter.submitList(cities)

        return binding.root
    }
}