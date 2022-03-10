package com.numbad.numba.newsapp.domain.model.bdd

import com.numbad.numba.newsapp.domain.model.News
import io.realm.RealmObject

open class BddNews_BddArticle: RealmObject() {
    var id: Int = 0
    var author: String = ""
    var date: String = ""
    var image: String = ""
    var sport: String = ""
    var teaser: String = ""
    var title: String = ""
}

fun BddNews_BddArticle.toStory(): News.Story {
    return News.Story(
        id = id,
        author = author,
        date = date,
        image = image,
        sport = sport,
        teaser = teaser,
        title = title
    )
}
