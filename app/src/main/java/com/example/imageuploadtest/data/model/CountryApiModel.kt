package com.example.imageuploadtest.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountryApiModel(
    val iso: Int,
    val isoAlpha2: String,
    val isoAlpha3: String,
    val name: String,
    val phonePrefix: String
)