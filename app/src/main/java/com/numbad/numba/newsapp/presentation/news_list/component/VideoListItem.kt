package com.numbad.numba.newsapp.presentation.news_list.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.numbad.numba.newsapp.R
import com.numbad.numba.newsapp.domain.model.News
import com.numbad.numba.newsapp.presentation.news_list.NewsListTag

@ExperimentalCoilApi
@Composable
fun VideoListItem(
    video: News.Video,
    onItemClick: (News.Video) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp
    ) {
        Column {

            Box(modifier = Modifier.height(200.dp)){
                val painter = rememberImagePainter(video.url)
                Box {
                    Image(
                        painter = painter,
                        contentDescription = video.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Image(
                        painter = painterResource(id = R.drawable.play),
                        contentDescription = video.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize().align(Alignment.Center)
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    NewsListTag()
                }
            }
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)) {
                Text(
                    text = video.title,
                    color = colorResource(id = R.color.white),
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
}
