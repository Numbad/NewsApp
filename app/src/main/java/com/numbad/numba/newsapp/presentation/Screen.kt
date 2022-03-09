package com.numbad.numba.newsapp.presentation

sealed class Screen(val route: String) {
    object NewsListScreen: Screen("news_list_screen")
    object VideoDetailScreen: Screen("video_detail_screen")
    object ArticleDetailScreen: Screen("article_detail_screen")
}