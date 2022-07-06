package com.akocharyan.crypto.features.currencies.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Currency(
    val id: String,
    val name: String,
    val symbol: String
) : Parcelable
