package com.akocharyan.cachereader.features.cache.data.local

import android.net.Uri
import com.akocharyan.core.util.network.Error
import com.akocharyan.core.util.network.State
import com.akocharyan.cachereader.features.cache.data.model.CacheDto
import com.akocharyan.cachereader.features.cache.domain.CacheRepository
import javax.inject.Inject

class CacheRepositoryImpl @Inject constructor(private val dataSource: CacheDataSource) : CacheRepository {

    override fun getCache(uri: Uri): State<Error, List<CacheDto>> {
        val result = dataSource.fetchCache(uri)
        return if (result.isNotEmpty()) {
            State.Success(result)
        } else {
            State.Failure(Error.FilesNotFound)
        }
    }
}
