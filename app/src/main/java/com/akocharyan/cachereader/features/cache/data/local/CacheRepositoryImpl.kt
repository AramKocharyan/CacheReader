package com.akocharyan.cachereader.features.cache.data.local

import android.net.Uri
import com.akocharyan.cachereader.features.cache.data.model.CacheDto
import com.akocharyan.cachereader.features.cache.domain.CacheRepository
import com.akocharyan.core.models.Error
import com.akocharyan.core.models.PagedList
import com.akocharyan.core.models.State
import javax.inject.Inject

class CacheRepositoryImpl @Inject constructor(private val dataSource: CacheDataSource) : CacheRepository {

    override suspend fun getCache(uri: Uri, nextPage: Int, pageSize: Int): State<Error, PagedList<CacheDto>> {
        val result = dataSource.fetchCache(
            uri = uri,
            nextPage = nextPage,
            pageSize = pageSize,
        )
        return if (result.data.isEmpty()) {
            State.Failure(Error.FilesNotFound)
        } else {
            State.Success(result)
        }
    }
}
