package ru.netology.tututesttask.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.netology.tututesttask.databinding.FragmentWeatherBinding
import ru.netology.tututesttask.ui.CityFragment.Companion.textArg
import ru.netology.tututesttask.viewmodel.WeatherViewModel

class WeatherFragment : Fragment() {
    private val viewModel: WeatherViewModel by viewModels(ownerProducer = ::requireParentFragment)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentWeatherBinding.inflate(inflater, container, false)

//        arguments?.textArg?.let(binding.tvCity::setText)
        binding.tvCity.text = arguments?.textArg

        viewModel.weatherData.observe(viewLifecycleOwner) { forecastModel ->
            binding.tvTemp.text = forecastModel.fact.temp.toString()
        }

        return binding.root
    }

    fun printTemperature(lat: Double, lon: Double) {

    }
}