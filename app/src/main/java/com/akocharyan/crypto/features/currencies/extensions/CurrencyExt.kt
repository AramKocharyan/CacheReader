package com.akocharyan.crypto.features.currencies.extensions

import com.akocharyan.crypto.features.currencies.data.model.CurrencyDto
import com.akocharyan.crypto.features.currencies.domain.model.Currency

fun CurrencyDto.toCurrency() = Currency(id, name, symbol)
