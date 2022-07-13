package com.akocharyan.crypto.features.currencies.domain.models

enum class AppEnum(val cacheDir: String) {
    TELEGRAM("org.telegram.messenger/cache"),
    WHATSAPP("–"),
    VIBER("–")
}
