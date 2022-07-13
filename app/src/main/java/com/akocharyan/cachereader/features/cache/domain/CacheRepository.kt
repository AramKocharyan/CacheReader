package com.akocharyan.cachereader.features.cache.domain

import android.net.Uri
import com.akocharyan.core.util.network.Error
import com.akocharyan.core.util.network.State
import com.akocharyan.cachereader.features.cache.data.model.CacheDto

interface CacheRepository {

    fun getCache(uri: Uri): State<Error, List<CacheDto>>
}
