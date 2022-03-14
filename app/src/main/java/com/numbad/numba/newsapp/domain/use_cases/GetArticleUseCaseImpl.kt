package com.numbad.numba.newsapp.domain.use_cases

import com.numbad.numba.newsapp.common.Resource
import com.numbad.numba.newsapp.domain.model.News
import com.numbad.numba.newsapp.domain.model.bdd.toStory
import com.numbad.numba.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

interface GetArticleUseCase {
    operator fun invoke(id: Int): Flow<Resource<News.Story?>>
}

class GetArticleUseCaseImpl @Inject constructor(
    private val repository: NewsRepository
): GetArticleUseCase {
    override operator fun invoke(id: Int): Flow<Resource<News.Story?>> = flow {
        try {
            emit(Resource.Loading())
            val article = repository.getArticleFromBdd(id)?.toStory()
            emit(Resource.Success<News.Story?>(article))
        } catch (e: Exception) {
            emit(Resource.Error<News.Story?>(e.localizedMessage ?: "Unexpected error occurred"))
        }
    }
}