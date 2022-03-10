package com.numbad.numba.newsapp.presentation.news_list

import com.numbad.numba.newsapp.common.Resource
import com.numbad.numba.newsapp.domain.model.News
import junit.framework.Assert
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class NewsListVMTest {
    class GetNewsUseCase: com.numbad.numba.newsapp.domain.use_cases.GetNewsUseCase {
        var resource: Resource<News>? = null
        override operator fun invoke() = flow<Resource<News>> {
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
        val useCase = GetNewsUseCase()
        useCase.resource = error
        val viewModel = NewsListVM(useCase)
        Assert.assertEquals(NewsListState(false, emptyList(), "An error occurred"), viewModel.state.value)
    }

    @Test
    fun `Test states when loading`() {
        val useCase = GetNewsUseCase()
        useCase.resource = loading
        val viewModel = NewsListVM(useCase)
        Assert.assertEquals(NewsListState(true, emptyList(), ""), viewModel.state.value)
    }

    @Test
    fun `Test states when success`() {
        val useCase = GetNewsUseCase()
        useCase.resource = success
        val viewModel = NewsListVM(useCase)
        Assert.assertEquals(
            NewsListState(
                false,
                listOf(Pair(News.Story(
                    author = "EuroSport",
                    date = "10-11-2022 10: 25",
                    id = 0,
                    image = "link",
                    sport = "Tennis",
                    teaser = "teaser",
                    title = "title"
                ), News.Video(
                    date = "10-11-2022 10: 25",
                    id = 2,
                    sport = "football",
                    thumb = "link",
                    title = "title",
                    url = "link Video",
                    views = 1000))),
                ""
            ), viewModel.state.value
        )
    }

    val error = Resource.Error<News>()
    val success = Resource.Success<News>(
        data = News(
            stories = listOf(
                News.Story(
                    author = "EuroSport",
                    date = "10-11-2022 10: 25",
                    id = 0,
                    image = "link",
                    sport = "Tennis",
                    teaser = "teaser",
                    title = "title"
                )
            ),
            videos = listOf(
                News.Video(
                    date = "10-11-2022 10: 25",
                    id = 2,
                    sport = "football",
                    thumb = "link",
                    title = "title",
                    url = "link Video",
                    views = 1000
                )
            )
        )
    )
    val loading = Resource.Loading<News>()
}