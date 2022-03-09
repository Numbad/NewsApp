package com.numbad.numba.newsapp.domain.repository

import com.numbad.numba.newsapp.data.remote.dto.NewsDto

interface NewsRepository {
    suspend fun getNews(): NewsDto
}