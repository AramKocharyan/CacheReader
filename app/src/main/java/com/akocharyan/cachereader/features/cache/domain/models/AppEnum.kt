package com.akocharyan.cachereader.features.cache.domain.models

enum class AppEnum(val cacheDir: String) {
    TELEGRAM("org.telegram.messenger/cache"),
    WHATSAPP("–"),
    VIBER("–")
}
