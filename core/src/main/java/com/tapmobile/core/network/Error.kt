package com.tapmobile.core.network

sealed interface Error {
    object NetworkConnection : Error
    object ServerError : Error
    object None : Error

    interface FeatureError : Error
}
