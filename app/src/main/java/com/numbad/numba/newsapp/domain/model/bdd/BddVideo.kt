package com.numbad.numba.newsapp.domain.model.bdd


import com.numbad.numba.newsapp.domain.model.News
import io.realm.RealmObject

open class BddNews_BddVideo: RealmObject() {
    var id: Int = 0
    var date: String = ""
    var sport: String = ""
    var thumb: String = ""
    var title: String = ""
    var url: String = ""
    var views: Int  = 0
}

fun BddNews_BddVideo.toVideo(): News.Video {
    return News.Video(
        id = id,
        date = date,
        sport = sport,
        thumb = thumb,
        title = title,
        url = url,
        views = views
    )
}