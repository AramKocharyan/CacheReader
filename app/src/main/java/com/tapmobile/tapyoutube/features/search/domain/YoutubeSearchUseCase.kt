package com.tapmobile.tapyoutube.features.search.domain

import com.tapmobile.core.interactor.UseCase
import com.tapmobile.core.network.Error
import com.tapmobile.core.network.State
import com.tapmobile.tapyoutube.features.search.data.YoutubeSearchRepository
import com.tapmobile.tapyoutube.features.search.domain.models.YoutubeVideoDto
import javax.inject.Inject

class YoutubeSearchUseCase @Inject constructor(private val moviesRepository: YoutubeSearchRepository) :
    UseCase<List<YoutubeVideoDto>, YoutubeSearchUseCase.Params> {

    override suspend fun run(params: Params): State<Error, List<YoutubeVideoDto>> {
        return moviesRepository.searchVideo(params.query)
    }

    data class Params(val query: String?)

}
