package com.example.testwireless.ui.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testwireless.R
import com.example.testwireless.data.model.CountryModel
import com.example.testwireless.databinding.FragmentListCountriesBinding
import com.example.testwireless.ui.adapter.CountriesAdapter
import com.example.testwireless.ui.viewModel.CountryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListCountriesFragment : Fragment(), CountriesAdapter.OnCountryClickListener {
    private lateinit var binding: FragmentListCountriesBinding
    private val countryViewModel: CountryViewModel by viewModels()

    private var countriesAdapter: CountriesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListCountriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setupObserver()
        searchCountry()
    }

    private fun searchCountry() {
        binding.editextSearchCountry.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                countryViewModel.filterCountries(s.toString())
            }
        })
    }

    private fun initRecyclerView() {
        countriesAdapter = CountriesAdapter(this)

        with(binding.rcvCountries) {
            layoutManager = GridLayoutManager(this.context, 1)
            adapter = countriesAdapter
        }
    }

    private fun setupObserver() {
        countryViewModel.getDataCountryState().observe(viewLifecycleOwner) { state ->
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

    private fun showLoading() {
        binding.progressLoading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressLoading.visibility = View.GONE
    }

    override fun onCountryClicked(country: CountryModel) {
        val bundle = Bundle().apply {
            putSerializable("country", country)
        }
        findNavController().navigate(
            R.id.action_listCountriesFragment_to_detailCountryFragment, bundle
        )
    }
}