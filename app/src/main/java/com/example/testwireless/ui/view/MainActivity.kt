package com.example.testwireless.ui.view

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testwireless.R
import com.example.testwireless.databinding.ActivityMainBinding
import com.example.testwireless.ui.adapter.CountriesAdapter
import com.example.testwireless.ui.viewModel.CountryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val countryViewModel: CountryViewModel by viewModels()

    private var countriesAdapter: CountriesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initRecyclerView()
        setupObserver()
        countryViewModel.onCreate()
    }

    fun initRecyclerView() {
        countriesAdapter = CountriesAdapter()

        with(binding.rcvCountries) {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            adapter = countriesAdapter
        }
    }

    private fun setupObserver() {
        countryViewModel.getDataCountryState().observe(
            this
        ) { state ->
            when (state) {
                is CountryViewModel.GetDataCountryState.Loading -> {
                    showLoading()
                }

                is CountryViewModel.GetDataCountryState.DataLoaded -> {
                    hideLoading()
                     countriesAdapter?.setCountries(state.heroResponseResult)
                }

                is CountryViewModel.GetDataCountryState.Error -> {
                    state.message
                }
            }
        }
    }

    fun showLoading() {
        binding.progressLoading.setVisibility(View.VISIBLE)
    }

    fun hideLoading() {
        binding.progressLoading.setVisibility(View.GONE)
    }
}