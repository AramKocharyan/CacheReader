package com.akocharyan.ultimatearch.features.search.presentation

import androidx.lifecycle.viewModelScope
import com.akocharyan.core.platorm.BaseViewModel
import com.akocharyan.ultimatearch.features.search.domain.models.YoutubeVideoDto
import com.akocharyan.ultimatearch.features.search.domain.YoutubeSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class YoutubeSearchViewModel
@Inject constructor(private val useCase: YoutubeSearchUseCase) : BaseViewModel() {

    val youtubeVideoModelList = MutableStateFlow<List<YoutubeVideoDto>>(emptyList())

    fun searchVideo(query: String?) {
        useCase(YoutubeSearchUseCase.Params(query), viewModelScope) {
            fold(::handleFailure, ::handleVideosFound)
        }
    }

    private fun handleVideosFound(list: List<YoutubeVideoDto>) {

    }

}
