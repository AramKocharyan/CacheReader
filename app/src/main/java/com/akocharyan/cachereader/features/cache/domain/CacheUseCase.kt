package com.akocharyan.cachereader.features.cache.domain

import android.net.Uri
import com.akocharyan.cachereader.features.cache.data.model.CacheDto
import com.akocharyan.core.interactor.UseCase
import com.akocharyan.core.models.Error
import com.akocharyan.core.models.PagedList
import com.akocharyan.core.models.State
import javax.inject.Inject

class CacheUseCase
@Inject constructor(private val cacheRepository: CacheRepository) : UseCase<PagedList<CacheDto>, CacheUseCase.Params> {

    override suspend fun run(params: Params): State<Error, PagedList<CacheDto>> =
        cacheRepository.getCache(params.cacheUri, params.nextPage, params.pageSize)

    data class Params(val cacheUri: Uri, val nextPage: Int, val pageSize: Int)

}
