package com.example.testwireless.data

import com.example.testwireless.data.model.CountryModel
import com.example.testwireless.data.network.CountryService
import javax.inject.Inject

class CountryRepository @Inject constructor(private val api: CountryService) {

    var countries: List<CountryModel> = emptyList()

    suspend fun getAllCountries(): List<CountryModel> {
        if (countries.isNotEmpty()) return countries
        val countries = api.getCountry()
        return countries
    }

}