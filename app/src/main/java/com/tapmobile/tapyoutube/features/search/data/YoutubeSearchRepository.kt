package com.tapmobile.tapyoutube.features.search.data

import com.tapmobile.core.network.Error
import com.tapmobile.core.network.State
import com.tapmobile.tapyoutube.features.search.domain.models.YoutubeVideoDto
import javax.inject.Inject

interface YoutubeSearchRepository {

    suspend fun searchVideo(query: String?): State<Error, List<YoutubeVideoDto>>
}

class YoutubeSearchRepositoryImpl
@Inject constructor(val service: YoutubeSearchService) : YoutubeSearchRepository {

    override suspend fun searchVideo(query: String?): State<Error, List<YoutubeVideoDto>> {
        val result = service.search(query)
        return State.Failure(Error.ServerError)
    }
}
