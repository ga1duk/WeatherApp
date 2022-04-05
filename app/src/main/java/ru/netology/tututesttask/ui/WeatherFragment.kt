package ru.netology.tututesttask.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.netology.tututesttask.R
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

        viewModel.weatherData.observe(viewLifecycleOwner) { forecastModel ->
            binding.tvCity.text = forecastModel.geo_object?.locality?.name ?: arguments?.textArg
            binding.tvTemp.text = "${forecastModel.fact.temp} ${resources.getString(R.string.degrees_by_celsius_text)}"
        }

        return binding.root
    }
}