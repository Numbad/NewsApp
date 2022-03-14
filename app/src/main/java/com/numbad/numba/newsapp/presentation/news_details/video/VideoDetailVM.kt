package com.numbad.numba.newsapp.presentation.news_details.video

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.numbad.numba.newsapp.common.Constant
import com.numbad.numba.newsapp.common.Resource
import com.numbad.numba.newsapp.domain.use_cases.GetVideoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class VideoDetailVM @Inject constructor(
    private val getVideoUseCase: GetVideoUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(VideoDetailState())
    val state: State<VideoDetailState> = _state

    init {
        savedStateHandle.get<String>(Constant.VIDEO_ID)?.let { videoId ->
            getVideo(videoId.toInt())
        }
    }

    private fun getVideo(videoId: Int) {
        getVideoUseCase(videoId).onEach { res ->
            when(res) {
                is Resource.Loading -> {
                    _state.value = VideoDetailState(isLoading = true)
                }
                is Resource.Success -> {
                    if(res.data != null){
                        _state.value = VideoDetailState(video = res.data)
                    }
                }
                is Resource.Error -> {
                    _state.value = VideoDetailState(errorMessage = res.message ?: "An error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}