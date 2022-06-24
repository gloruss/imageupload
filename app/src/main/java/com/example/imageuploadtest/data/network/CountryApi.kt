package com.example.imageuploadtest.data.network

import com.example.imageuploadtest.data.model.CountryApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface CountryApi {

    @GET("countries/")

    @Headers("x-api-key:AIzaSyCccmdkjGe_9Yt-INL2rCJTNgoS4CXsRDc")
    suspend fun getCountry() : Response<List<CountryApiModel>>
}