package com.numbad.numba.newsapp.presentation.news_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.numbad.numba.newsapp.R
import com.numbad.numba.newsapp.presentation.LaunchScreen
import com.numbad.numba.newsapp.presentation.Screen
import com.numbad.numba.newsapp.presentation.news_list.component.ArticleListItem
import com.numbad.numba.newsapp.presentation.news_list.component.VideoListItem
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsListScreen(
    navController: NavController,
    viewModel: NewsListVM = hiltViewModel()
) {
    val state = viewModel.state.value
    Scaffold(
        topBar = {
            if(!state.isLoading)
            TopAppBar(

                title = { Text(text="FEATURED", textAlign = TextAlign.Center) },
                navigationIcon = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        painterResource(id = R.drawable.back)
                    }
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        painterResource(id = R.drawable.share)
                    }
                },
                backgroundColor = (colorResource(id = R.color.primary)),

            )

        },
        content = { innerPadding ->
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 5.dp)
                ) {
                    //val state = viewModel.state.value
                    items(state.news) { (article, video) ->
                        if (video != null) {
                            VideoListItem(
                                video = video,
                                onItemClick = {
                                    val encodedUrl = URLEncoder.encode(
                                        video.url,
                                        StandardCharsets.UTF_8.toString()
                                    )
                                    navController.navigate(Screen.VideoDetailScreen.route + "/${encodedUrl}")
                                }
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                        }
                        if (article != null) {
                            ArticleListItem(article = article, onItemClick = {
                                navController.navigate(Screen.ArticleDetailScreen.route + "/${article.id}")
                            })
                            Spacer(modifier = Modifier.height(15.dp))
                        }

                    }
                }
            }
            if(state.errorMessage.isNotBlank()) {
                Text(
                    text = state.errorMessage,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
            }
            if(state.isLoading) {
                LaunchScreen()
            }
        }
    )
}

