package com.akocharyan.crypto.features.currencies.domain.helper.di

import com.akocharyan.crypto.features.currencies.data.network.CurrenciesRepositoryImpl
import com.akocharyan.crypto.features.currencies.domain.CurrenciesRepository
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
