package com.akocharyan.cachereader.features.cache.domain

import android.net.Uri
import com.akocharyan.core.models.Error
import com.akocharyan.core.models.State
import com.akocharyan.cachereader.features.cache.data.model.CacheDto
import com.akocharyan.core.models.PagedList

interface CacheRepository {

    suspend fun getCache(uri: Uri, nextPage: Int, pageSize: Int): State<Error, PagedList<CacheDto>>
}
