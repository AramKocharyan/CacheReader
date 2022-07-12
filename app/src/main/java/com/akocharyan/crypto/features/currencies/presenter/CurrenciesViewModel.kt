package com.akocharyan.crypto.features.currencies.presenter

import androidx.lifecycle.viewModelScope
import com.akocharyan.core.interactor.UseCase
import com.akocharyan.core.platorm.BaseViewModel
import com.akocharyan.crypto.features.currencies.domain.CurrenciesUseCase
import com.akocharyan.crypto.features.currencies.domain.model.Currency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(private val currencies: CurrenciesUseCase) : BaseViewModel() {

    private val _currenciesFlow: MutableStateFlow<List<Currency>> = MutableStateFlow(emptyList())
    val currenciesFlow: StateFlow<List<Currency>> = _currenciesFlow

    init {
        loadCurrencies()
    }

    fun loadCurrencies() = currencies(viewModelScope, UseCase.None) { fold(::handleFailure, ::handleCurrencyList) }

    private fun handleCurrencyList(currencies: List<Currency>) {
        _currenciesFlow.value = currencies
    }

}
