package ru.netology.myweatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.myweatherapp.databinding.CardCityBinding
import ru.netology.myweatherapp.dto.CityModel

interface OnCityClickListener {
    fun onNextScreenOpen(cityModel: CityModel) {}
}

class CityAdapter(private val onCityClickListener: OnCityClickListener) :
    ListAdapter<CityModel, CityViewHolder>(CityDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val binding = CardCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityViewHolder(binding, onCityClickListener)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = getItem(position)
        holder.bind(city)
    }
}

class CityViewHolder(
    private val binding: CardCityBinding,
    private val onCityClickListener: OnCityClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(cityModel: CityModel) {
        binding.tvCity.text = cityModel.city

        binding.tvCity.setOnClickListener {
            onCityClickListener.onNextScreenOpen(cityModel)
        }
    }
}

class CityDiffCallback : DiffUtil.ItemCallback<CityModel>() {
    override fun areItemsTheSame(oldItem: CityModel, newItem: CityModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CityModel, newItem: CityModel): Boolean {
        return oldItem == newItem
    }
}