package com.akocharyan.ultimatearch.features.search.domain

import com.akocharyan.core.interactor.UseCase
import com.akocharyan.core.network.Error
import com.akocharyan.core.network.State
import com.akocharyan.ultimatearch.features.search.data.YoutubeSearchRepository
import com.akocharyan.ultimatearch.features.search.domain.models.YoutubeVideoDto
import javax.inject.Inject

class YoutubeSearchUseCase @Inject constructor(private val moviesRepository: YoutubeSearchRepository) :
    UseCase<List<YoutubeVideoDto>, YoutubeSearchUseCase.Params> {

    override suspend fun run(params: Params): State<Error, List<YoutubeVideoDto>> {
        return moviesRepository.searchVideo(params.query)
    }

    data class Params(val query: String?)

}
