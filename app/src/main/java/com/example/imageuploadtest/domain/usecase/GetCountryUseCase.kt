package com.example.imageuploadtest.domain.usecase

import com.example.imageuploadtest.data.repository.CountryRepository
import com.example.imageuploadtest.domain.model.Country
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCountryUseCase @Inject constructor(
    private val repository: CountryRepository
) {
    suspend  operator fun invoke() : Flow<List<Country>> =
        repository.getCountries()
}