package com.akocharyan.crypto.features.currencies.data.network

import com.akocharyan.core.network.Error
import com.akocharyan.core.network.State
import com.akocharyan.core.network.util.NetworkHandler
import com.akocharyan.core.network.util.request
import com.akocharyan.crypto.features.currencies.domain.CurrenciesRepository
import com.akocharyan.crypto.features.currencies.domain.helper.extensions.toCurrency
import com.akocharyan.crypto.features.currencies.domain.model.Currency
import javax.inject.Inject

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
