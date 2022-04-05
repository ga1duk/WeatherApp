package ru.netology.tututesttask.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.tututesttask.R
import ru.netology.tututesttask.adapter.CityAdapter
import ru.netology.tututesttask.adapter.OnClickListener
import ru.netology.tututesttask.databinding.FragmentCityBinding
import ru.netology.tututesttask.util.StringArg
import ru.netology.tututesttask.viewmodel.CityViewModel

class CityFragment : Fragment() {

    private val cityViewModel: CityViewModel by viewModels(ownerProducer = ::requireParentFragment)

    companion object {
        var Bundle.textArg: String? by StringArg
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCityBinding.inflate(inflater, container, false)


        val adapter = CityAdapter(object : OnClickListener {
            override fun onNextScreenOpen(city: String) {
                findNavController().navigate(R.id.action_cityFragment_to_weatherFragment,
                    Bundle().apply {
                        textArg = city
                    })
            }
        })
        binding.rvCity.adapter = adapter

        cityViewModel.data.observe(viewLifecycleOwner) { cities ->
            adapter.submitList(cities)
        }

        return binding.root
    }
}