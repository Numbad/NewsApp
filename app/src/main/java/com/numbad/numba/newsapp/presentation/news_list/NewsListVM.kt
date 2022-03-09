package com.numbad.numba.newsapp.presentation.news_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.numbad.numba.newsapp.common.Resource
import com.numbad.numba.newsapp.domain.model.News
import com.numbad.numba.newsapp.domain.use_cases.GetNewsUseCase
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
                    val dishes: MutableList<Pair<News.Story?, News.Video>> = mutableListOf()
                    res.data?.stories?.zip(res.data.videos)?.map {
                        dishes.add(it)
                    }
                    //_state.value = NewsListState(dishes = res.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = NewsListState(errorMessage = res.message ?: "An error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}