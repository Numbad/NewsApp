package com.numbad.numba.newsapp.presentation.news_details.article

import androidx.lifecycle.SavedStateHandle
import com.numbad.numba.newsapp.common.Resource
import com.numbad.numba.newsapp.domain.model.News
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class ArticleDetailsVMTest {
    class GetArticleUseCase: com.numbad.numba.newsapp.domain.use_cases.GetArticleUseCase {
        var resource: Resource<News.Story?>? = null
        override operator fun invoke(id: Int) = flow<Resource<News.Story?>> {
            emit(resource ?: Resource.Error())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Test states when error occurred`() {
        val savedStateHandle = SavedStateHandle().apply {
            set("articleId", "1")
        }
        val useCase = GetArticleUseCase()
        useCase.resource = error
        val viewModel = ArticleDetailsVM(useCase, savedStateHandle)
        assertEquals(ArticleDetailState(false, null, "An error occurred"), viewModel.state.value)
    }

   @Test
    fun `Test states when loading`() {
        val savedStateHandle = SavedStateHandle().apply {
            set("articleId", "1")
        }
        val useCase = GetArticleUseCase()
        useCase.resource = loading
        val viewModel = ArticleDetailsVM(useCase, savedStateHandle)
        assertEquals(ArticleDetailState(true, null, ""), viewModel.state.value)
    }

  @Test
    fun `Test states when success`() {
        val savedStateHandle = SavedStateHandle().apply {
            set("articleId", "1")
        }
        val useCase = GetArticleUseCase()
        useCase.resource = success
        val viewModel = ArticleDetailsVM(useCase, savedStateHandle)
        assertEquals(ArticleDetailState(
            false,
            News.Story(
                author = "EuroSport",
                date = "10-11-2022 10: 25",
                id = 0,
                image = "link",
                sport = "Tennis",
                teaser = "teaser",
                title = "title"
            ),
            ""
        ), viewModel.state.value)
    }

    val error = Resource.Error<News.Story?>()
    val success = Resource.Success<News.Story?>(data = News.Story(
        author = "EuroSport",
        date = "10-11-2022 10: 25",
        id = 0,
        image = "link",
        sport = "Tennis",
        teaser = "teaser",
        title = "title"
    ))
    val loading = Resource.Loading<News.Story?>()
}