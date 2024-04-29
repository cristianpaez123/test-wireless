package com.example.testwireless.domain

import com.example.testwireless.data.CountryRepository
import com.example.testwireless.data.model.CountryModel
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(private val repository: CountryRepository) {

    suspend operator fun invoke():List<CountryModel> = repository.getAllCountries()
}