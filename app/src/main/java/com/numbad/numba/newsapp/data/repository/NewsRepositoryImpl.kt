package com.numbad.numba.newsapp.data.repository

import com.numbad.numba.newsapp.domain.model.bdd.BddNews
import com.numbad.numba.newsapp.domain.model.bdd.BddNews_BddArticle
import com.numbad.numba.newsapp.data.remote.NewsApi
import com.numbad.numba.newsapp.data.remote.dto.NewsDto
import com.numbad.numba.newsapp.domain.model.News
import com.numbad.numba.newsapp.domain.model.bdd.BddNews_BddVideo
import com.numbad.numba.newsapp.domain.repository.NewsRepository
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import java.util.*
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi,
    private val realm: Realm
): NewsRepository {
    companion object {
       // val config = RealmConfiguration.Builder(
       //     schema = setOf(
       //         BddNews::class,
       //         BddNews_BddVideo::class,
       //         BddNews_BddArticle::class
       //     )
       // ).build()
       // val realm = Realm.open(config)
    }


    override suspend fun getNews(): NewsDto {
        return api.getNews()
    }

    override suspend fun getNewsFromBdd(): BddNews? {
        return realm.where<BddNews>().findFirst()
    }

    override suspend fun addNewsToBdd(news: News): BddNews {
        realm.beginTransaction()
        val newsBdd = realm.createObject<BddNews>()
        newsBdd.stories.addAll(news.stories.map { article ->
            val artBdd = realm.createObject<BddNews_BddArticle>()
            artBdd.id = article.id
            artBdd.author = article.author
            artBdd.image = article.image
            artBdd.sport = article.sport
            artBdd.teaser = article.teaser
            artBdd.title = article.title
            artBdd.date = article.date
            artBdd
        })
        newsBdd.videos.addAll(news.videos.map { video ->
            val videoBdd = realm.createObject<BddNews_BddVideo>()
            videoBdd.id = video.id
            videoBdd.date = video.date
            videoBdd.sport = video.sport
            videoBdd.thumb = video.thumb
            videoBdd.title = video.title
            videoBdd.url = video.url
            videoBdd.views = video.views
            videoBdd
        })
        realm.commitTransaction()



        return newsBdd
    }

    override suspend fun getFromBddArticle(id: Int): BddNews_BddArticle? {
        return realm.where<BddNews_BddArticle>().equalTo("id", id).findFirst()
    }
}