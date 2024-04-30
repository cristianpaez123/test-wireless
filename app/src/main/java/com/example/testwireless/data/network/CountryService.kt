package com.example.testwireless.data.network

import com.example.testwireless.data.model.CountryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CountryService @Inject constructor(private val api: CountryApi) {

    suspend fun getCountry(): List<CountryModel> {
        return withContext(Dispatchers.IO) {
            val respose = api.getAllCountries()
            respose ?: emptyList()
        }
    }
}