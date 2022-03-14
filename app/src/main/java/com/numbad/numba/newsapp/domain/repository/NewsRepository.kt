package com.numbad.numba.newsapp.domain.repository

import com.numbad.numba.newsapp.domain.model.bdd.BddNews
import com.numbad.numba.newsapp.domain.model.bdd.BddNews_BddArticle
import com.numbad.numba.newsapp.data.remote.dto.NewsDto
import com.numbad.numba.newsapp.domain.model.News
import com.numbad.numba.newsapp.domain.model.bdd.BddNews_BddVideo

interface NewsRepository {
    suspend fun getNews(): NewsDto
    suspend fun getNewsFromBdd(): BddNews?
    suspend fun addNewsToBdd(news: News): BddNews
    suspend fun getArticleFromBdd(id: Int): BddNews_BddArticle?
    suspend fun getVideoFromBdd(id: Int): BddNews_BddVideo?
}