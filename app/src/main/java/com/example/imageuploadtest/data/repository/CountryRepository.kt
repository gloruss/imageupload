package com.example.imageuploadtest.data.repository

import com.example.imageuploadtest.data.model.CountryApiModel
import com.example.imageuploadtest.domain.model.Country
import kotlinx.coroutines.flow.Flow

interface CountryRepository {

    suspend fun getCountries() : Flow<List<Country>>

}