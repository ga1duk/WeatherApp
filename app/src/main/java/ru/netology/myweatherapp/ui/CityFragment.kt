package ru.netology.myweatherapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.myweatherapp.R
import ru.netology.myweatherapp.adapter.CityAdapter
import ru.netology.myweatherapp.adapter.OnCityClickListener
import ru.netology.myweatherapp.databinding.FragmentCityBinding
import ru.netology.myweatherapp.dto.CityModel
import ru.netology.myweatherapp.dto.ForecastModel
import ru.netology.myweatherapp.util.DoubleArg
import ru.netology.myweatherapp.util.StringArg
import ru.netology.myweatherapp.viewmodel.WeatherViewModel

class CityFragment : Fragment() {

    private val viewModel: WeatherViewModel by viewModels(ownerProducer = ::requireParentFragment)

    companion object {
        var Bundle.textArg: String? by StringArg
        var Bundle.doubleArgLat: Double? by DoubleArg
        var Bundle.doubleArgLon: Double? by DoubleArg
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCityBinding.inflate(inflater, container, false)

        val adapter = CityAdapter(object : OnCityClickListener {
            override fun onNextScreenOpen(cityModel: CityModel) {
                findNavController().navigate(R.id.action_cityFragment_to_weatherFragment,
                    Bundle().apply {
                        textArg = cityModel.city
                        doubleArgLat = cityModel.lat
                        doubleArgLon = cityModel.lon
                        viewModel.loadWeather(cityModel.lat, cityModel.lon)
                    })

            }
        })
        binding.rvCity.adapter = adapter

        viewModel.cityData.observe(viewLifecycleOwner) { cities ->
            adapter.submitList(cities)
        }

        return binding.root
    }
}