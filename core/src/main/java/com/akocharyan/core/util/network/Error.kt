package com.akocharyan.core.util.network

sealed interface Error {
    object NetworkConnection : Error
    object ServerError : Error
    object FilesNotFound : Error
    object None : Error

    interface FeatureError : Error
}
