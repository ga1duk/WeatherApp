package ru.netology.tututesttask.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.netology.tututesttask.databinding.FragmentWeatherBinding
import ru.netology.tututesttask.viewmodel.WeatherViewModel

class WeatherFragment : Fragment() {

    private val viewModel: WeatherViewModel by viewModels(ownerProducer = ::requireParentFragment)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentWeatherBinding.inflate(inflater, container, false)

        viewModel.data.observe(viewLifecycleOwner) { forecast ->
            binding.tvTemp.text = forecast.fact.temp.toString()
        }

        return binding.root
    }
}