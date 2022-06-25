package com.example.imageuploadtest.utils

import com.example.imageuploadtest.data.model.CountryApiModel
import com.example.imageuploadtest.domain.model.Country


const val ISO = 248
const val ISOALPHA2 = "AX"
const val ISOALPHA3 = "ALA"
const val NAME = "Aland Islands"
const val PHONEPREF ="+0039"

fun createCountryApiModel(
    iso : Int = ISO,
    isoAlpha2 : String = ISOALPHA2,
    isoAlpha3: String = ISOALPHA3,
    name : String = NAME,
    phonePref : String = PHONEPREF
) = CountryApiModel(
    iso = iso,
    isoAlpha2 = isoAlpha2,
    isoAlpha3 = isoAlpha3,
    name = name,
    phonePrefix = phonePref
)

fun createCountryModel(
    name: String = NAME
) = Country(name = name)



