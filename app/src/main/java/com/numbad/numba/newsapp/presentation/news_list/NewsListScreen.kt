package com.numbad.numba.newsapp.presentation.news_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.numbad.numba.newsapp.presentation.Screen
import com.numbad.numba.newsapp.presentation.news_list.component.VideoListItem
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.numbad.numba.newsapp.presentation.LaunchScreen


@Composable
fun NewsListScreen(
    navController: NavController,
    viewModel: NewsListVM = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.news) { (article, video) ->
                VideoListItem(
                    video = video,
                    onItemClick = {
                        navController.navigate(Screen.VideoDetailScreen.route + "/${video.url}")
                    }
                )
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
                    .align(Alignment.Center)
            )
        }
        if(state.isLoading) {
            LaunchScreen()
        }
    }
}