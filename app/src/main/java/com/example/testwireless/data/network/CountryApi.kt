package com.example.testwireless.data.network

import com.example.testwireless.data.model.CountryModel
import retrofit2.http.GET

interface CountryApi {
    @GET("all")
    suspend fun getAllCountries(): List<CountryModel>
}