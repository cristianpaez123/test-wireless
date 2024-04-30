package com.example.testwireless.ui.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.testwireless.R
import com.example.testwireless.data.model.CountryModel
import com.example.testwireless.databinding.ActivityDetailCountryBinding
import com.example.testwireless.databinding.ActivityMainBinding
import com.example.testwireless.ui.adapter.CountriesAdapter
import com.example.testwireless.ui.viewModel.CountryViewModel
import com.example.testwireless.ui.viewModel.DetailCountryViewModel
import com.squareup.picasso.Picasso
const val MAIN_CAPITAL_POSITION = 0
class DetailCountry : AppCompatActivity() {

    private lateinit var binding: ActivityDetailCountryBinding
    private val detailCountryViewModel: DetailCountryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCountryBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detailCountry)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        getCountry()
        setupObserver()
    }

    private fun setupObserver() {
        detailCountryViewModel.country.observe(this, Observer { country ->
            Picasso.get().load(country.flag.png).into(binding.imvFlag)
            binding.apply {
                txtCountry.text = country.name?.official
                txtRegion.text = country.region
                txtSubRegion.text = country.subregion
                txtArea.text = country.area.toString()
                txtFifa.text = country.fifa
                txtMap.text = country.map.openStreetMaps
                txtMap.setOnClickListener { goMapWebSite(country.map.openStreetMaps) }
                country.capital?.let {
                    binding.txtCapital.text = country.capital[MAIN_CAPITAL_POSITION]
                } ?: run {
                    binding.txtCapital.text = "NO TIENE CAPITAL"
                }
            }
        })
    }

    fun getCountry() {
        val countryModel: CountryModel = intent.getSerializableExtra("country") as CountryModel
        detailCountryViewModel.setCountry(countryModel)
    }

    fun goMapWebSite(url:String){
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}