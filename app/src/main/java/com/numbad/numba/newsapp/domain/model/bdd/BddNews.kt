package com.numbad.numba.newsapp.domain.model.bdd

import com.numbad.numba.newsapp.domain.model.News
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.Ignore
import java.util.*


open class BddNews: RealmObject() {
    var objectTimed: ObjectTimed? = ObjectTimed()
    var stories = RealmList<BddNews_BddArticle>()
    var videos = RealmList<BddNews_BddVideo>()
}

fun BddNews.toNews(): News {
    return News(
        stories = this.stories.map { it.toStory() },
        videos = this.videos.map { it.toVideo() }
    )
}

open class ObjectTimed(var lastUpdate: Date = Date()): RealmObject() {
    @Ignore
    var validityTime: Double = (60 * 5).toDouble()

    fun needUpdate(): Boolean{
        val calendar = Calendar.getInstance()
        calendar.time = lastUpdate
        calendar.add(Calendar.SECOND, validityTime.toInt())

        return calendar.time <= Date()
    }
}