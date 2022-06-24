package com.example.imageuploadtest.di


import com.example.imageuploadtest.data.repository.CountryRepository
import com.example.imageuploadtest.data.repository.CountryRepositoryImpl
import com.example.imageuploadtest.data.repository.ImageUploadRepository
import com.example.imageuploadtest.data.repository.ImageUploadRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideCountryRepository(countryRepository: CountryRepositoryImpl) : CountryRepository

    @Binds
    @Singleton
    abstract fun provideUploadRepository(imageUploadRepository: ImageUploadRepositoryImpl) : ImageUploadRepository
}