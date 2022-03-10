package com.numbad.numba.newsapp.presentation.news_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.numbad.numba.newsapp.common.Resource
import com.numbad.numba.newsapp.domain.model.News
import com.numbad.numba.newsapp.domain.use_cases.GetNewsUseCase
import com.numbad.numba.newsapp.domain.use_cases.GetNewsUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NewsListVM @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {
    private val _state = mutableStateOf(NewsListState())
    val state: State<NewsListState> = _state

    init {
        getNews()
    }

    private fun getNews() {
        getNewsUseCase().onEach { res ->
            when(res) {
                is Resource.Loading -> {
                    _state.value = NewsListState(isLoading = true)
                }
                is Resource.Success -> {
                    val news: MutableList<Pair<News.Story?, News.Video?>> = mutableListOf()
                    if(res.data != null){
                        res.data.stories
                            .zip(res.data.videos)
                            .map { news.add(it) }
                        val articleSize = res.data.stories.size
                        val videoSize = res.data.videos.size
                        if(articleSize > videoSize){
                            res.data.stories.subList(videoSize - 1, articleSize -1).map {
                                news.add(Pair(it, null))
                            }
                        } else if(videoSize > articleSize) {
                            res.data.videos.subList(articleSize - 1, videoSize -1).map {
                                news.add(Pair(null, it))
                            }
                        }
                        _state.value = NewsListState(news = news)
                    }
                }
                is Resource.Error -> {
                    _state.value = NewsListState(errorMessage = res.message ?: "An error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}