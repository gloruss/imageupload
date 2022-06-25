package com.example.imageuploadtest.data.repository

import com.example.imageuploadtest.data.network.CountryApi
import com.example.imageuploadtest.data.network.toNetworkError
import com.example.imageuploadtest.domain.mapper.CountryApiToDomainMapper
import com.example.imageuploadtest.domain.model.Country
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val mapper : CountryApiToDomainMapper,
    private val api : CountryApi
) :CountryRepository {

    override suspend fun getCountries(): List<Country> =

            api.getCountry().let {
                response ->
                if(response.isSuccessful){
                    val countries = response.body()?.map {
                        mapper.map(it)
                    }?.sortedBy { it.name }
                    countries ?: emptyList()
                }
                else{
                    throw response.code().toNetworkError()
                }
            }

}