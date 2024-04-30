package com.example.testwireless.domain

import com.example.testwireless.data.CountryRepository
import com.example.testwireless.data.model.CountryModel
import javax.inject.Inject

class GetCountriesByQueryUseCase @Inject constructor(private val repository: CountryRepository) {

    suspend operator fun invoke(query: String): List<CountryModel> =
        repository.getAllCountries().filter {
            it.name?.official?.toLowerCase()?.contains(query.toLowerCase()) ?: false
        }
}