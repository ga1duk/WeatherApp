package ru.netology.tututesttask.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.tututesttask.databinding.CardCityBinding
import ru.netology.tututesttask.dto.CityModel

interface OnClickListener {
    fun onNextScreenOpen(city: String) {}
}

class CityAdapter(private val onClickListener: OnClickListener) : ListAdapter<CityModel, CityViewHolder>(CityDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val binding = CardCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = getItem(position)
        holder.bind(city)
    }
}

class CityViewHolder(
    private val binding: CardCityBinding,
    private val onClickListener: OnClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(cityModel: CityModel) {
        binding.tvCity.text = cityModel.city

        binding.tvCity.setOnClickListener {
            onClickListener.onNextScreenOpen(cityModel.city)
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