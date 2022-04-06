package ru.netology.tututesttask.ui

import android.graphics.drawable.PictureDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import ru.netology.tututesttask.R
import ru.netology.tututesttask.databinding.FragmentWeatherBinding
import ru.netology.tututesttask.ui.CityFragment.Companion.textArg
import ru.netology.tututesttask.viewmodel.WeatherViewModel

class WeatherFragment : Fragment() {
    private var requestBuilder: RequestBuilder<PictureDrawable>? = null
    private val viewModel: WeatherViewModel by viewModels(ownerProducer = ::requireParentFragment)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentWeatherBinding.inflate(inflater, container, false)
        requestBuilder = Glide.with(this)
            .`as`(PictureDrawable::class.java)

        viewModel.weatherData.observe(viewLifecycleOwner) { forecastModel ->
            binding.tvCity.text = forecastModel.geo_object?.locality?.name ?: arguments?.textArg
            requestBuilder?.load(forecastModel.fact.getConditionIcon())?.into(binding.ivIcon)
            binding.tvTemp.text = "${forecastModel.fact.temp} ${resources.getString(R.string.degrees_by_celsius_text)}"
            binding.tvCondition.text = forecastModel.fact.condition
        }

        return binding.root
    }
}