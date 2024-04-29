package com.example.testwireless.ui.viewModel

import androidx.lifecycle.LiveData
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

    private val dataCountryState: MutableLiveData<GetDataCountryState> = MutableLiveData()
    fun getDataCountryState(): LiveData<GetDataCountryState> = dataCountryState
    fun onCreate() {
        dataCountryState.postValue(GetDataCountryState.Loading)
        viewModelScope.launch {
            val resultCountrys = getCountriesUseCase()
            try {
                dataCountryState.postValue(GetDataCountryState.DataLoaded(resultCountrys))
            } catch (e: Exception) {
                dataCountryState.postValue(GetDataCountryState.Error("ERROR"))
            }
        }
    }

    sealed class GetDataCountryState() {
        object Loading : GetDataCountryState()
        data class DataLoaded(val heroResponseResult: List<CountryModel>) : GetDataCountryState()
        data class Error(val message: String) : GetDataCountryState()
    }
}
