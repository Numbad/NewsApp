package com.numbad.numba.newsapp.domain.use_cases

import com.numbad.numba.newsapp.common.Resource
import com.numbad.numba.newsapp.data.remote.dto.toNews
import com.numbad.numba.newsapp.domain.model.News
import com.numbad.numba.newsapp.domain.model.bdd.toNews
import com.numbad.numba.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


interface GetNewsUseCase {
    operator fun invoke(): Flow<Resource<News>>
}

class GetNewsUseCaseImpl @Inject constructor(
    private val repository: NewsRepository
): GetNewsUseCase {
    override operator fun invoke(): Flow<Resource<News>> = flow {
        try {
            emit(Resource.Loading())
            val newsBdd = repository.getNewsFromBdd()
            if(newsBdd!= null && !newsBdd.objectTimed!!.needUpdate()) {
                emit(Resource.Success<News>(newsBdd.toNews()))
            } else {
                val news = repository.getNews().toNews()
                repository.addNewsToBdd(news)
                emit(Resource.Success<News>(news))
            }
        } catch (e: HttpException) {
            emit(Resource.Error<News>(e.localizedMessage ?: "Unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<News>("Couldn't reach server. Check your connexion"))
        }
    }
}