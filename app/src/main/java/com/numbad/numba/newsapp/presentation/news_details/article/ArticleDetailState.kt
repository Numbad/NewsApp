package com.numbad.numba.newsapp.presentation.news_details.article

import com.numbad.numba.newsapp.domain.model.News

data class ArticleDetailState(
    val isLoading: Boolean = false,
    val article: News.Story? = null,
    val errorMessage: String = ""
)
