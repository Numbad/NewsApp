package com.numbad.numba.newsapp.presentation.news_details.video

import com.numbad.numba.newsapp.domain.model.News

data class VideoDetailState(
    val isLoading: Boolean = false,
    val video: News.Video? = null,
    val errorMessage: String = "",
)
