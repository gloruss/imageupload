package com.example.imageuploadtest.domain.mapper

import com.example.imageuploadtest.data.model.CountryApiModel
import com.example.imageuploadtest.domain.model.Country
import javax.inject.Inject

class CountryApiToDomainMapper @Inject constructor(){

    fun map(input : CountryApiModel) : Country{
        return Country(
            name = input.name
        )
    }
}