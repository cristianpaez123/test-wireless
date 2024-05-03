package com.example.testwireless.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.testwireless.data.model.CountryModel
import com.example.testwireless.databinding.FragmentDetailCountryBinding
import com.example.testwireless.ui.viewModel.DetailCountryViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

const val MAIN_CAPITAL_POSITION = 0
const val NO_CAPITAL = "NO TIENE CAPITAL"

@AndroidEntryPoint
class DetailCountryFragment : Fragment() {
    private lateinit var binding: FragmentDetailCountryBinding
    private val detailCountryViewModel: DetailCountryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailCountryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCountry()
        setupObserver()
    }


    private fun setupObserver() {
        detailCountryViewModel.country.observe(viewLifecycleOwner, Observer { country ->
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
                    binding.txtCapital.text = NO_CAPITAL
                }
            }
        })
    }

    private fun getCountry() {
        val country = arguments?.getSerializable("country") as CountryModel
        detailCountryViewModel.setCountry(country)
    }

    private fun goMapWebSite(url: String) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}