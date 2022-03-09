package com.numbad.numba.newsapp.domain.use_cases

import com.numbad.numba.newsapp.common.Resource
import com.numbad.numba.newsapp.data.remote.dto.toNews
import com.numbad.numba.newsapp.domain.model.News
import com.numbad.numba.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<Resource<News>> = flow {
        try {
            emit(Resource.Loading())
            val news = repository.getNews().toNews()
            emit(Resource.Success(news))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your connexion"))
        }
    }
}