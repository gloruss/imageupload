package com.example.imageuploadtest.presentation.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imageuploadtest.domain.model.Country
import com.example.imageuploadtest.domain.model.DataState
import com.example.imageuploadtest.domain.usecase.GetCountryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val countryUseCase: GetCountryUseCase
) : ViewModel()  {

    private val _countryFlow = MutableStateFlow<DataState<List<Country>>>(DataState(loading = true))
    val countryFlow : StateFlow<DataState<List<Country>>> = _countryFlow


    init {
        getCountryList()
    }


    fun getCountryList(){
        viewModelScope.launch {
            try {
                val list = countryUseCase.invoke()

                    _countryFlow.value = DataState(list)
            }
            catch (e : Exception){
                _countryFlow.value = DataState(exception = e.message)
            }
        }
    }
}