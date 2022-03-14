package com.numbad.numba.newsapp.presentation.news_details.article

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.numbad.numba.newsapp.R
import com.numbad.numba.newsapp.presentation.news_list.NewsListTag

@Composable
fun ArticleDetailsScreen(
    viewModel: ArticleDetailsVM = hiltViewModel(),
    navHostController: NavHostController,
    context: Context
) {
    val state = viewModel.state.value
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = {
                        navHostController.popBackStack()
                    }) {
                        Icon(
                            tint = colorResource(id = R.color.white),
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        val sendIntent: Intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, "Do you want to share this story ?")
                            type = "text/plain"
                        }
                        val shareIntent = Intent.createChooser(sendIntent, null)
                        context.startActivity(shareIntent)
                    }) {
                        Icon(
                            tint = colorResource(id = R.color.white),
                            imageVector = Icons.Filled.Share,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },
        content = {
            Column {
                state.article?.let { article ->
                    val painter = rememberImagePainter(data = article.image)
                    Box(  modifier = Modifier
                        .height(200.dp).padding(top = 20.dp)) {
                        Image(
                            painter = painter,
                            contentDescription = "decription",
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .height(200.dp)
                                .fillMaxSize()
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp),
                            contentAlignment = Alignment.BottomStart
                        ) {
                           // NewsListTag(article.sport)
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp)
                    ) {
                        Text(
                            text = article.title,
                            color = colorResource(id = R.color.black),
                            textAlign = TextAlign.Left,
                            style = MaterialTheme.typography.h5
                        )
                        Text(
                            text = "By ${article.author}",
                            color = colorResource(id = R.color.date_color),
                            textAlign = TextAlign.Left,
                            style = MaterialTheme.typography.body2
                        )

                        Text(
                            text = article.date,
                            color = colorResource(id = R.color.date_color),
                            textAlign = TextAlign.Left,
                            style = MaterialTheme.typography.body1
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = article.teaser,
                            textAlign = TextAlign.Left,
                            style = MaterialTheme.typography.h6
                        )
                    }
                }

                if (state.errorMessage.isNotBlank()) {
                    Text(
                        text = state.errorMessage,
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }

            }


        }
    )

}