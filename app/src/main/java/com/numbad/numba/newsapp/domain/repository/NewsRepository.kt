package com.numbad.numba.newsapp.domain.repository

import com.numbad.numba.newsapp.domain.model.bdd.BddNews
import com.numbad.numba.newsapp.domain.model.bdd.BddNews_BddArticle
import com.numbad.numba.newsapp.data.remote.dto.NewsDto
import com.numbad.numba.newsapp.domain.model.News

interface NewsRepository {
    suspend fun getNews(): NewsDto
    suspend fun getNewsFromBdd(): BddNews?
    suspend fun addNewsToBdd(news: News): BddNews
    suspend fun getFromBddArticle(id: Int): BddNews_BddArticle?
}