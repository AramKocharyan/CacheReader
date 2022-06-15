package com.tapmobile.tapyoutube.features.search.data

import com.tapmobile.tapyoutube.core.network.Error
import com.tapmobile.tapyoutube.core.network.State
import com.tapmobile.tapyoutube.features.search.domain.models.YoutubeVideoDto
import javax.inject.Inject

interface YoutubeSearchRepository {

    suspend fun searchVideo(query: String?): State<Error, List<YoutubeVideoDto>>
}

class YoutubeSearchRepositoryImpl
@Inject constructor() : YoutubeSearchRepository {

    override suspend fun searchVideo(query: String?): State<Error, List<YoutubeVideoDto>> {

        return State.Failure(Error.ServerError)
    }
}
