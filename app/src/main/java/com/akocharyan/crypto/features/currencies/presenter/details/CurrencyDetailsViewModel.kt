package com.akocharyan.crypto.features.currencies.presenter.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.akocharyan.core.platorm.BaseViewModel
import com.akocharyan.crypto.features.currencies.domain.CurrencyDetailsUseCase
import com.akocharyan.crypto.features.currencies.domain.model.Currency
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrencyDetailsViewModel @Inject constructor(private val currencyDetails: CurrencyDetailsUseCase) : BaseViewModel() {

    private val _currencyLiveData: MutableLiveData<Currency> = MutableLiveData()
    val currencyLiveData: LiveData<Currency> = _currencyLiveData

    fun loadCurrencyById(id: String) =
        currencyDetails(viewModelScope, CurrencyDetailsUseCase.Params(id)) { fold(::handleFailure, ::handleCurrency) }

    private fun handleCurrency(currency: Currency?) {
        _currencyLiveData.value = currency
    }

}
