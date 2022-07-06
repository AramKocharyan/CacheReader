package com.akocharyan.crypto.features.currencies.di

import com.akocharyan.crypto.features.currencies.domain.CurrenciesRepository
import com.akocharyan.crypto.features.currencies.domain.CurrenciesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CurrenciesModule {

    @Provides
    @Singleton
    fun provideCurrenciesRepository(dataSource: CurrenciesRepositoryImpl): CurrenciesRepository = dataSource

}
