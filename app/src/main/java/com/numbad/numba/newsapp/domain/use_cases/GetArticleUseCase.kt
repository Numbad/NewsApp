package com.numbad.numba.newsapp.domain.use_cases

import com.numbad.numba.newsapp.common.Resource
import com.numbad.numba.newsapp.domain.model.News
import com.numbad.numba.newsapp.domain.model.bdd.toStory
import com.numbad.numba.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetArticleUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(id: Int): Flow<Resource<News.Story?>> = flow {
        try {
            emit(Resource.Loading())
            val article = repository.getFromBddArticle(id)?.toStory()
            emit(Resource.Success<News.Story?>(article))
        } catch (e: HttpException) {
            emit(Resource.Error<News.Story?>(e.localizedMessage ?: "Unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<News.Story?>("Couldn't reach server. Check your connexion"))
        }
    }
}