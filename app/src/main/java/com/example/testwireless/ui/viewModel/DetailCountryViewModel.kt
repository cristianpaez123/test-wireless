package com.example.testwireless.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testwireless.data.model.CountryModel

class DetailCountryViewModel : ViewModel() {

    private val _country = MutableLiveData<CountryModel>()
    val country:LiveData<CountryModel>
        get() = _country

    fun setCountry(country: CountryModel){
        _country.value = country
    }
}