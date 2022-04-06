package ru.netology.tututesttask.ui

import android.graphics.drawable.PictureDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import ru.netology.tututesttask.R
import ru.netology.tututesttask.databinding.FragmentWeatherBinding
import ru.netology.tututesttask.glide.GlideApp
import ru.netology.tututesttask.ui.CityFragment.Companion.doubleArgLat
import ru.netology.tututesttask.ui.CityFragment.Companion.doubleArgLon
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

        requestBuilder = GlideApp.with(this)
            .`as`(PictureDrawable::class.java)

        viewModel.weatherDataState.observe(viewLifecycleOwner) { state ->
            binding.progress.isVisible = state.loading
            binding.swipeRefreshLayout.isRefreshing = state.refreshing
            binding.errorGroup.isVisible = state.error
            binding.forecastGroup.isVisible = state.forecast
        }

        viewModel.weatherData.observe(viewLifecycleOwner) { weatherModel ->
            binding.tvCity.text = weatherModel.geo_object?.locality?.name ?: arguments?.textArg
            requestBuilder?.load(weatherModel.fact?.getConditionIcon())?.into(binding.icCondition)
            GlideApp.with(this)
                .load(weatherModel.fact?.getConditionIcon())
                .into(binding.icCondition)

            binding.tvTemp.text =
                "${weatherModel.fact?.temp} ${resources.getString(R.string.degrees_by_celsius_text)}"
            binding.tvCondition.text = weatherModel.fact?.condition
        }

        binding.btnRetry.setOnClickListener {
            viewModel.loadWeather(arguments?.doubleArgLat!!, arguments?.doubleArgLon!!)
            println("${arguments?.doubleArgLat!!}, ${arguments?.doubleArgLon!!}")
        }

        with(binding.swipeRefreshLayout) {
            setOnRefreshListener {
                viewModel.refreshWeather(arguments?.doubleArgLat!!, arguments?.doubleArgLon!!)
            }
            setColorSchemeResources(
                R.color.purple_700
            )
            setSize(CircularProgressDrawable.LARGE)
        }

        return binding.root
    }
}