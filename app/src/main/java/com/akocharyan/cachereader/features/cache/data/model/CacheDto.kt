package com.akocharyan.cachereader.features.cache.data.model

data class CacheDto(
    val id: String,
    val name: String,
    val ext: String,
    val size: Long
)

val CacheDto.sizeKB: String
    get() = String.format("%.2f KB", size / 1024f)
