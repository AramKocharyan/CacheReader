package com.akocharyan.crypto.features.currencies.domain

import com.akocharyan.core.network.Error
import com.akocharyan.core.network.State
import com.akocharyan.crypto.features.currencies.domain.model.Currency

interface CurrenciesRepository {
    fun getCurrencies(): State<Error, List<Currency>>
    fun getCurrencyById(id: String): State<Error, Currency?>
}

