package com.numbad.numba.newsapp.data.remote.dto

import com.numbad.numba.newsapp.domain.model.News
import java.io.Serializable
import java.util.*

data class NewsDto(
    val stories: List<Story>,
    val videos: List<Video>
): Serializable {
    data class Story(
        val author: String,
        val date: Double,
        val id: Int,
        val image: String,
        val sport: Sport,
        val teaser: String,
        val title: String
    ): Serializable
    data class Sport(
        val id: Int,
        val name: String
    ): Serializable

    data class Video(
        val date: Double,
        val id: Int,
        val sport: Sport,
        val thumb: String,
        val title: String,
        val url: String,
        val views: Int
    )
}

fun NewsDto.toNews(): News {
    return News(stories = stories.map { it.toStory() }.sortedBy { it.date }, videos = videos.map { it.toVideo() }.sortedBy { it.date })
}

fun NewsDto.Story.toStory(): News.Story {
    val timestamp: Long = date.toLong()
    val timeD = Date(timestamp * 1000)

    return News.Story(author, timeD, id, image, sport.name, teaser, title)
}

fun NewsDto.Video.toVideo(): News.Video {
    val timestamp: Long = date.toLong()
    val timeD = Date(timestamp * 1000)
    return News.Video(timeD, id, sport.name, thumb, title, url, views)
}