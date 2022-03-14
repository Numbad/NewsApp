package com.numbad.numba.newsapp.domain.use_cases

import com.numbad.numba.newsapp.common.Resource
import com.numbad.numba.newsapp.domain.model.News
import com.numbad.numba.newsapp.domain.model.bdd.toVideo
import com.numbad.numba.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface GetVideoUseCase {
    operator fun invoke(id: Int): Flow<Resource<News.Video?>>
}

class GetVideoUseCaseImpl @Inject constructor(
    private val repository: NewsRepository
): GetVideoUseCase {
    override operator fun invoke(id: Int): Flow<Resource<News.Video?>> = flow {
        try {
            emit(Resource.Loading())
            val video = repository.getVideoFromBdd(id)?.toVideo()
            emit(Resource.Success<News.Video?>(video))
        } catch (e: Exception) {
            emit(Resource.Error<News.Video?>(e.localizedMessage ?: "Unexpected error occurred"))
        }
    }
}