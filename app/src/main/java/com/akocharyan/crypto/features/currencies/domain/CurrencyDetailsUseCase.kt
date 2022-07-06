package com.akocharyan.crypto.features.currencies.domain

import com.akocharyan.core.interactor.UseCase
import com.akocharyan.core.network.Error
import com.akocharyan.core.network.State
import com.akocharyan.crypto.features.currencies.domain.model.Currency
import javax.inject.Inject

class CurrencyDetailsUseCase
@Inject constructor(private val currenciesRepository: CurrenciesRepository) :
    UseCase<Currency?, CurrencyDetailsUseCase.Params> {

    override suspend fun run(params: Params): State<Error, Currency?> = currenciesRepository.getCurrencyById(params.currencyId)

    data class Params(val currencyId: String)

}
