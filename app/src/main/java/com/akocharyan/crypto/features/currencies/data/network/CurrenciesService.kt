package com.akocharyan.crypto.features.currencies.data.network

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrenciesService
@Inject constructor(retrofit: Retrofit) : CurrenciesApi {
    private val currenciesApi by lazy { retrofit.create(CurrenciesApi::class.java) }

    override fun getCurrencies() = currenciesApi.getCurrencies()
}
