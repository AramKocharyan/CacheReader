package com.akocharyan.crypto.features.currencies.domain

import com.akocharyan.core.network.Error
import com.akocharyan.core.network.State
import com.akocharyan.core.network.util.NetworkHandler
import com.akocharyan.core.network.util.request
import com.akocharyan.crypto.features.currencies.data.network.CurrenciesService
import com.akocharyan.crypto.features.currencies.domain.model.Currency
import com.akocharyan.crypto.features.currencies.extensions.toCurrency
import javax.inject.Inject

interface CurrenciesRepository {
    fun getCurrencies(): State<Error, List<Currency>>
    fun getCurrencyById(id: String): State<Error, Currency?>
}

class CurrenciesRepositoryImpl
@Inject constructor(
    private val networkHandler: NetworkHandler,
    private val service: CurrenciesService
) : CurrenciesRepository {

    override fun getCurrencies(): State<Error, List<Currency>> {
        return request(
            service.getCurrencies(),
            { it.map { currencyEntity -> currencyEntity.toCurrency() } },
            emptyList(),
            networkHandler
        )
    }

    override fun getCurrencyById(id: String): State<Error, Currency?> {
        return request(
            service.getCurrencies(),
            { it.find { currencyEntity -> currencyEntity.id == id }?.toCurrency() },
            emptyList(),
            networkHandler
        )
    }

}
