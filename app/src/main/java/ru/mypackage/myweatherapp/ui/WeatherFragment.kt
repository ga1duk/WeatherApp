package ru.mypackage.myweatherapp.ui

import android.graphics.drawable.PictureDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.RequestBuilder
import ru.mypackage.myweatherapp.R
import ru.mypackage.myweatherapp.databinding.FragmentWeatherBinding
import ru.mypackage.myweatherapp.glide.GlideApp
import ru.mypackage.myweatherapp.ui.CityFragment.Companion.doubleArgLat
import ru.mypackage.myweatherapp.ui.CityFragment.Companion.doubleArgLon
import ru.mypackage.myweatherapp.viewmodel.WeatherViewModel

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
            binding.tvCity.setText(weatherModel.city)
            requestBuilder?.load(weatherModel.icon)?.into(binding.icCondition)
            GlideApp.with(this)
                .load(weatherModel.icon)
                .into(binding.icCondition)

            binding.tvTemp.text = resources.getString(
                R.string.current_temperature_text,
                weatherModel.temp,
                resources.getString(R.string.degrees_by_celsius_text)
            )
            binding.tvCondition.setText(weatherModel.condition)
        }

        binding.btnRetry.setOnClickListener {
            viewModel.loadWeather(arguments?.doubleArgLat ?: 0.0, arguments?.doubleArgLon ?: 0.0)
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