package com.numbad.numba.newsapp.data.remote.dto

import com.numbad.numba.newsapp.common.SlashDateFormatter
import com.numbad.numba.newsapp.domain.model.bdd.BddNews_BddArticle
import com.numbad.numba.newsapp.domain.model.bdd.BddNews_BddVideo
import com.numbad.numba.newsapp.domain.model.News
import com.numbad.numba.newsapp.domain.model.bdd.BddNews
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
    val stringDate = SlashDateFormatter().format(timeD)
    return News.Story(author, stringDate, id, image, sport.name, teaser, title)
}

fun NewsDto.Video.toVideo(): News.Video {
    val timestamp: Long = date.toLong()
    val timeD = Date(timestamp * 1000)
    val stringDate = SlashDateFormatter().format(timeD)
    return News.Video(stringDate, id, sport.name, thumb, title, url, views)
}

fun NewsDto.Story.toBddStory(): BddNews_BddArticle {
    val story = this.toStory()
    val bdd = BddNews_BddArticle()
    bdd.author = story.author
    bdd.date = story.date
    bdd.image = story.image
    bdd.sport = story.sport
    bdd.teaser = story.teaser
    bdd.title = story.title

    return bdd
}

fun NewsDto.Video.toBddVideo(): BddNews_BddVideo {
    val video = this.toVideo()
    val bdd = BddNews_BddVideo()
    bdd.id = video.id
    bdd.date = video.date
    bdd.thumb = video.thumb
    bdd.sport = video.sport
    bdd.url = video.url
    bdd.views = video.views

    return bdd
}

fun NewsDto.toNewsBdd(): BddNews {
    val bdd = BddNews()
     this.stories.map { bdd.stories.add(it.toBddStory()) }
     this.videos.map { bdd.videos.add(it.toBddVideo()) }
    return bdd
}