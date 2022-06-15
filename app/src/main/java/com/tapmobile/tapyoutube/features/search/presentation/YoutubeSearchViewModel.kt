package com.tapmobile.tapyoutube.features.search.presentation

import androidx.lifecycle.viewModelScope
import com.tapmobile.tapyoutube.core.network.LoadingState
import com.tapmobile.tapyoutube.core.platorm.BaseViewModel
import com.tapmobile.tapyoutube.features.search.domain.models.YoutubeVideoDto
import com.tapmobile.tapyoutube.features.search.domain.YoutubeSearchUseCase
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
