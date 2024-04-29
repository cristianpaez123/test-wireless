package com.example.testwireless.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testwireless.data.model.CountryModel
import com.example.testwireless.domain.GetCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase
) : ViewModel() {

    val countriesModel = MutableLiveData<List<CountryModel>>()

    fun onCreate() {
        viewModelScope.launch {
            val resultCountrys = getCountriesUseCase()
            countriesModel.postValue(resultCountrys)
        }


    }
}