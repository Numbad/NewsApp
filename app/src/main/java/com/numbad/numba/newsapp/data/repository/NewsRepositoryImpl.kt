package com.numbad.numba.newsapp.data.repository

import com.numbad.numba.newsapp.data.remote.NewsApi
import com.numbad.numba.newsapp.data.remote.dto.NewsDto
import com.numbad.numba.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi
): NewsRepository {
    override suspend fun getNews(): NewsDto {
        return api.getNews()
    }
}