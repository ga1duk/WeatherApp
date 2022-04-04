package ru.netology.tututesttask.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.netology.tututesttask.R
import ru.netology.tututesttask.databinding.FragmentCityBinding
import ru.netology.tututesttask.databinding.FragmentWeatherBinding

class CityFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCityBinding.inflate(inflater, container, false)

        binding.btnGo.setOnClickListener {
            findNavController().navigate(R.id.action_cityFragment_to_weatherFragment)
        }

        return binding.root
    }
}