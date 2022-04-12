package ru.netology.myweatherapp.ui

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
import ru.netology.myweatherapp.R
import ru.netology.myweatherapp.databinding.FragmentWeatherBinding
import ru.netology.myweatherapp.dto.*
import ru.netology.myweatherapp.glide.GlideApp
import ru.netology.myweatherapp.ui.CityFragment.Companion.doubleArgLat
import ru.netology.myweatherapp.ui.CityFragment.Companion.doubleArgLon
import ru.netology.myweatherapp.ui.CityFragment.Companion.textArg
import ru.netology.myweatherapp.viewmodel.WeatherViewModel

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

            binding.tvTemp.text = resources.getString(
                R.string.current_temperature_text,
                weatherModel.fact?.temp,
                resources.getString(R.string.degrees_by_celsius_text)
            )
            binding.tvCondition.text = getConditionLocalized(weatherModel)/*.toString()*/
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

    fun getConditionLocalized(weatherModel: ForecastModel): String {
        return when (weatherModel.fact?.condition) {
            CLEAR -> resources.getString(R.string.clear_conditions_text)
            PARTLY_CLOUDY -> resources.getString(R.string.partly_cloudy_conditions_text)
            CLOUDY -> resources.getString(R.string.cloudy_conditions_text)
            OVERCAST -> resources.getString(R.string.overcast_conditions_text)
            DRIZZLE -> resources.getString(R.string.drizzle_conditions_text)
            LIGHT_RAIN -> resources.getString(R.string.light_rain_conditions_text)
            RAIN -> resources.getString(R.string.rain_conditions_text)
            MODERATE_RAIN -> resources.getString(R.string.moderate_rain_conditions_text)
            HEAVY_RAIN -> resources.getString(R.string.heavy_rain_conditions_text)
            CONTINUOUS_HEAVY_RAIN -> resources.getString(R.string.continuous_heavy_rain_conditions_text)
            SHOWERS -> resources.getString(R.string.showers_conditions_text)
            WET_SNOW -> resources.getString(R.string.wet_snow_conditions_text)
            LIGHT_SNOW -> resources.getString(R.string.light_snow_conditions_text)
            SNOW -> resources.getString(R.string.snow_conditions_text)
            SNOW_SHOWERS -> resources.getString(R.string.snow_showers_conditions_text)
            HAIL -> resources.getString(R.string.hail_conditions_text)
            THUNDERSTORM -> resources.getString(R.string.thunderstorm_conditions_text)
            THUNDERSTORM_WITH_RAIN -> resources.getString(R.string.thunderstorm_with_rain_conditions_text)
            THUNDERSTORM_WITH_HAIL -> resources.getString(R.string.thunderstorm_with_hail_conditions_text)
            else -> weatherModel.fact!!.condition
        }
    }
}