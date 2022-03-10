package com.numbad.numba.newsapp.presentation.news_details.article

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.numbad.numba.newsapp.common.Constant
import com.numbad.numba.newsapp.common.Resource
import com.numbad.numba.newsapp.domain.use_cases.GetArticleUseCase
import com.numbad.numba.newsapp.domain.use_cases.GetArticleUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ArticleDetailsVM @Inject constructor(
    private val getArticleUseCase: GetArticleUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(ArticleDetailState())
    val state: State<ArticleDetailState> = _state

    init {
        savedStateHandle.get<String>(Constant.ARTICLE_ID)?.let { articleID ->
            getArticle(articleID.toInt())
        }
    }

    private fun getArticle(articleID: Int) {
        getArticleUseCase(articleID).onEach { res ->
            when(res) {
                is Resource.Loading -> {
                    _state.value = ArticleDetailState(isLoading = true)
                }
                is Resource.Success -> {
                    if(res.data != null){
                        _state.value = ArticleDetailState(article = res.data)
                    }
                }
                is Resource.Error -> {
                    _state.value = ArticleDetailState(errorMessage = res.message ?: "An error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}