package com.example.testwireless.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testwireless.R
import com.example.testwireless.data.model.CountryModel
import com.example.testwireless.databinding.ItemCountryAdapterBinding

class CountriesAdapter : RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>() {

    var dataCountries: List<CountryModel> = emptyList()

    fun setCountries(dataCountries: List<CountryModel>) {
        this.dataCountries = dataCountries
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemCountryAdapterBinding = DataBindingUtil.inflate(inflater, R.layout.item_country_adapter, parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = dataCountries[position]
        holder.bind(country)
    }

    override fun getItemCount(): Int {
        return dataCountries.size
    }

    inner class CountryViewHolder(private val binding: ItemCountryAdapterBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(country: CountryModel) {
            // Aquí asignas el objeto country al enlace de datos
            binding.country = country
            // Es importante ejecutar esto para que los cambios en los datos se reflejen en la vista
            binding.executePendingBindings()
        }
    }
}
