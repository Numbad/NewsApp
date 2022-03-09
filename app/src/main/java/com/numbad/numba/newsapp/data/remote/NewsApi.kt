package com.numbad.numba.newsapp.data.remote

import com.numbad.numba.newsapp.data.remote.dto.NewsDto
import retrofit2.http.GET

interface NewsApi {
    @GET("api/json-storage/bin/edfefba")
    suspend fun getNews(): NewsDto
}