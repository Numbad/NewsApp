package com.numbad.numba.newsapp.presentation.news_details

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.numbad.numba.newsapp.R
import com.numbad.numba.newsapp.common.Constant
import com.numbad.numba.newsapp.presentation.news_list.NewsListTag

@Composable
fun ArticleDetailsScreen(savedStateHandle: Bundle?, navHostController: NavHostController) {
    savedStateHandle?.get(Constant.ARTICLE_ID)?.let { id ->

    }
    /*Scaffold(
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
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = "Localized description"
                        )
                    }
                }, backgroundColor = colorResource(id = R.color.primary)
            )
        },
        content = {
            Column {
                Box(
                ){
                    val painter  = rememberImagePainter(data = article.thumb)

                    Box {
                        Image(
                            painter = painter,
                            contentDescription = "decription",
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier.height(200.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.play),
                            contentDescription = "description",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        NewsListTag(video.sport)
                    }
                }
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)) {
                    Text(
                        text = video.title,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Left,
                        style = MaterialTheme.typography.h6
                    )
                    Text(
                        text = "${video.views} views",
                        color = colorResource(id = R.color.date_color),
                        textAlign = TextAlign.Left,
                        style = MaterialTheme.typography.h6
                    )
                }

            }

        }
    )

}*/


}