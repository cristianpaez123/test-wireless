package com.example.testwireless.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testwireless.R
import com.example.testwireless.data.model.CountryModel
import com.example.testwireless.databinding.ItemCountryAdapterBinding
import com.squareup.picasso.Picasso

class CountriesAdapter(
    private val onCountryClickListener: OnCountryClickListener
) : RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>() {

    var dataCountries: List<CountryModel> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setCountries(dataCountries: List<CountryModel>) {
        this.dataCountries = dataCountries
        notifyDataSetChanged()
    }

    interface OnCountryClickListener {
        fun onCountryClicked(country: CountryModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemCountryAdapterBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_country_adapter, parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = dataCountries[position]
        holder.bind(country)
    }

    override fun getItemCount(): Int {
        return dataCountries.size
    }

    inner class CountryViewHolder(private val binding: ItemCountryAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(country: CountryModel) {
            binding.country = country

            country.capital?.let {
                binding.txtCapital.text = country.capital[MAIN_CAPITAL_POSITION]
            } ?: run {
                binding.txtCapital.text = NO_CAPITAL
            }
            binding.txtCountry.text = country.name?.official
            Picasso.get().load(country.flag.png).into(binding.imvFlag)
            binding.executePendingBindings()
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val country = dataCountries[position]
                    onCountryClickListener.onCountryClicked(country)
                }
            }
        }
    }

    companion object {
        const val MAIN_CAPITAL_POSITION = 0
        const val NO_CAPITAL = "NO TIENE CAPITAL"
    }
}
