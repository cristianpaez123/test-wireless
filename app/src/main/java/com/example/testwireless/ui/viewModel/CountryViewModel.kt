package com.example.testwireless.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testwireless.data.model.CountryModel
import com.example.testwireless.domain.GetCountriesByQueryUseCase
import com.example.testwireless.domain.GetCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getCountriesByQueryUseCase: GetCountriesByQueryUseCase
) : ViewModel() {

    private val dataCountryState: MutableLiveData<GetDataCountryState> = MutableLiveData()
    fun getDataCountryState(): LiveData<GetDataCountryState> = dataCountryState


    init {
        dataCountryState.postValue(GetDataCountryState.Loading)
        viewModelScope.launch {
            try {
                dataCountryState.postValue(GetDataCountryState.DataLoaded(getCountriesUseCase()))
            } catch (e: Exception) {
                dataCountryState.postValue(GetDataCountryState.Error("ERROR"))
            }
        }
    }

    fun filterCountries(query: String) {

        viewModelScope.launch {
            try {
                dataCountryState.postValue(
                    GetDataCountryState.DataLoaded(getCountriesByQueryUseCase(query))
                )
            } catch (e: Exception) {
                dataCountryState.postValue(GetDataCountryState.Error("ERROR"))
            }
        }
    }

    sealed class GetDataCountryState {
        data object Loading : GetDataCountryState()
        data class DataLoaded(val heroResponseResult: List<CountryModel>) : GetDataCountryState()
        data class Error(val message: String) : GetDataCountryState()
    }
}
