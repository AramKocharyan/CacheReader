package com.akocharyan.cachereader.features.cache.domain

import android.net.Uri
import com.akocharyan.core.interactor.UseCase
import com.akocharyan.core.util.network.Error
import com.akocharyan.core.util.network.State
import com.akocharyan.cachereader.features.cache.data.model.CacheDto
import javax.inject.Inject

class CacheUseCase
@Inject constructor(private val cacheRepository: CacheRepository) : UseCase<List<CacheDto>, CacheUseCase.Params> {

    override suspend fun run(params: Params): State<Error, List<CacheDto>> = cacheRepository.getCache(params.cacheUri)

    data class Params(val cacheUri: Uri)

}
