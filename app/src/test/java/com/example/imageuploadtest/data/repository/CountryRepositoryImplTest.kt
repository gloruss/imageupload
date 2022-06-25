package com.example.imageuploadtest.data.repository

import com.example.imageuploadtest.data.model.CountryApiModel
import com.example.imageuploadtest.data.network.CountryApi
import com.example.imageuploadtest.data.network.NetworkError
import com.example.imageuploadtest.domain.mapper.CountryApiToDomainMapper
import com.example.imageuploadtest.utils.JSON_SERVICE_UNAVAILABLE
import com.example.imageuploadtest.utils.createCountryApiModel
import com.example.imageuploadtest.utils.createCountryModel
import com.example.imageuploadtest.utils.fromJsonToResponseBody
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import okhttp3.Response
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class CountryRepositoryImplTest {

    @MockK
    lateinit var countryApi: CountryApi

    @MockK
    lateinit var countryMapper : CountryApiToDomainMapper

    private lateinit var repositoryImpl: CountryRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repositoryImpl = CountryRepositoryImpl(countryMapper,countryApi)
    }

    @Test
    fun `repository return country list when remote work as expected`(): Unit = runBlocking {

        val countryApiFirst = createCountryApiModel(name = "italia")
        val countryApiSecond = createCountryApiModel(name = "francia")
        val countryApiList = listOf(countryApiFirst,countryApiSecond)

        val countryFirst = createCountryModel(name = "Italia")
        val countrySecond = createCountryModel(name = "Francia")
        val countryList = listOf(countryFirst,countrySecond)

        val response = retrofit2.Response.success(countryApiList)
        //Stub service
        coEvery {  countryApi.getCountry()  } returns response

        //Stub mapper
        every { countryMapper.map(countryApiFirst) } returns countryFirst
        every { countryMapper.map(countryApiSecond) } returns countrySecond

        val countryListResponse = repositoryImpl.getCountries()
        countryListResponse.shouldContainExactly(countryList.sortedBy { it.name })
    }


    @Test
    fun `repository return empty list when remote body is empty`(): Unit = runBlocking {
        // Stub service
        val countryResponse = retrofit2.Response.success(emptyList<CountryApiModel>())
        coEvery { countryApi.getCountry() } returns countryResponse

        // Call repository
        val countryListResult = repositoryImpl.getCountries()

        // Assertion
        countryListResult.shouldBeEmpty()
    }


    @Test
    fun `repository throw exception with remote error`(): Unit = runBlocking {
        shouldThrow<NetworkError> {
            // Stub service
            val body = JSON_SERVICE_UNAVAILABLE.fromJsonToResponseBody()
            val response = retrofit2.Response.error<List<CountryApiModel>>(500, body)
            coEvery { countryApi.getCountry() } returns response

            // Call repository
            repositoryImpl.getCountries()
        }
    }

}