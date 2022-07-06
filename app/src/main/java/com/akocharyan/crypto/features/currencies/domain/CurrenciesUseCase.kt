package com.akocharyan.crypto.features.currencies.domain

import com.akocharyan.core.interactor.UseCase
import com.akocharyan.core.network.Error
import com.akocharyan.core.network.State
import com.akocharyan.crypto.features.currencies.domain.model.Currency
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject

class CurrenciesUseCase
@Inject constructor(private val currenciesRepository: CurrenciesRepository) : UseCase<List<Currency>, UseCase.None> {

    override suspend fun run(params: UseCase.None): State<Error, List<Currency>> = currenciesRepository.getCurrencies()

}
