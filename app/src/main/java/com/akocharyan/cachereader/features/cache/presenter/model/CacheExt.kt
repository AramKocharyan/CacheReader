package com.akocharyan.cachereader.features.cache.presenter.model

import com.akocharyan.cachereader.features.cache.data.model.CacheDto

val CacheDto.sizeKB: String
    get() = String.format("%.2f KB", size / 1024f)
