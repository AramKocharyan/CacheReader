package com.akocharyan.crypto.features.currencies.data.local

import android.net.Uri
import com.akocharyan.core.util.network.Error
import com.akocharyan.core.util.network.State
import com.akocharyan.crypto.features.currencies.data.model.CacheDto
import com.akocharyan.crypto.features.currencies.domain.CacheRepository
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
