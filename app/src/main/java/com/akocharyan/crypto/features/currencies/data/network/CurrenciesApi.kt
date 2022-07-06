package com.akocharyan.crypto.features.currencies.data.network

import com.akocharyan.crypto.features.currencies.data.model.CurrencyDto
import retrofit2.Call
import retrofit2.http.GET

interface CurrenciesApi {

    @GET(CURRENCIES)
    fun getCurrencies(): Call<List<CurrencyDto>>

    companion object {
        private const val CURRENCIES = "crypto.json"
    }
}
