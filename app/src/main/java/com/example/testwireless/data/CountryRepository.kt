package com.example.testwireless.data

import com.example.testwireless.data.model.CountryModel
import com.example.testwireless.data.network.CountryService
import javax.inject.Inject

class CountryRepository @Inject constructor(private val api: CountryService){
    suspend fun getAllCountries():List<CountryModel>{
        val response = api.getCountry()
        return response
    }
}