package com.numbad.numba.newsapp.domain.model

import java.util.*

data class News(
val stories: List<Story>,
val videos: List<Video>
) {
    data class Story(
        val author: String,
        val date: Date,
        val id: Int,
        val image: String,
        val sport: String,
        val teaser: String,
        val title: String
    )
    data class Video(
        val date: Date,
        val id: Int,
        val sport: String,
        val thumb: String,
        val title: String,
        val url: String,
        val views: Int
    )
}