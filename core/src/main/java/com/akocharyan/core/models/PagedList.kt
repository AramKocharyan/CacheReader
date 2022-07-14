package com.akocharyan.core.models

data class PagedList<T>(
    val data: List<T>,
    val nextPage: Int = 1,
    val totalCount: Int = 0
)
