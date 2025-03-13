package com.example.countriesdemo.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.countriesdemo.R
import com.example.countriesdemo.databinding.ItemCountryBinding
import com.example.countriesdemo.domain.models.Country

class CountriesAdapter: ListAdapter<Country, CountriesAdapter.CountryViewHolder>(DiffCallback()) {

    inner class CountryViewHolder(val binding: ItemCountryBinding): RecyclerView.ViewHolder(binding.root)

    class DiffCallback : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder =
        ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .let(::CountryViewHolder)

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = getItem(position)
        holder.binding.apply {
            countryName.text = root.context.getString(
                R.string.country_details,
                country.name,
                country.region
            )
            countryCode.text = country.code
            countryCapital.text = country.capital
        }
    }
}