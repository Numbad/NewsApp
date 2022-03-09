package com.numbad.numba.newsapp.presentation.news_list

import com.numbad.numba.newsapp.domain.model.News

data class NewsListState(
    val isLoading: Boolean = false,
    val news: List<Pair<News.Story?, News.Video>> = emptyList(),
    val errorMessage: String = ""
)
