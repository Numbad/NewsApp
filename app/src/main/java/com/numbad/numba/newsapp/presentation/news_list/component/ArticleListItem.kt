package com.numbad.numba.newsapp.presentation.news_list.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.widget.ConstraintLayout
import coil.compose.rememberImagePainter
import com.numbad.numba.newsapp.R
import com.numbad.numba.newsapp.domain.model.News
import com.numbad.numba.newsapp.presentation.news_list.NewsListTag

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ArticleListItem(
    article: News.Story,
    onItemClick: (News.Story) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp
    ) {

        Column {

            val painter  = rememberImagePainter(data = article.image)
            ConstraintLayout(
                modifier = Modifier
                    .height(400.dp)
                    .clickable { onItemClick(article) }
            )  {
                val (img, text) = createRefs()

                Image(
                    painter = painter,
                    contentDescription = "decription",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxSize()
                        .constrainAs(img) {
                            top.linkTo(parent.top)
                        }

                )
                NewsListTag(article.sport, modifier = Modifier
                    .constrainAs(text) {
                        top.linkTo(img.bottom)
                        bottom.linkTo(img.bottom)
                    })

               // NewsListTag(article.sport, modifier = Modifier
               //     .constrainAs(text) {
               //         end.linkTo(img.end)
               //     })
              //  Box(
              //      modifier = Modifier
              //          .fillMaxSize()
              //          .constrainAs(text) {
              //              top.linkTo(img.bottom)
              //              bottom.linkTo(img.bottom)
              //          },
              //      contentAlignment = Alignment.BottomStart
              //  ) {
              //      //ConstrainTop = Bottom img
              //      //Constraint bottom = bottom image
              //      // Constraint left =
              //      // Constraint left =
              //
              //  }


            }
           //Column(modifier = Modifier
           //    .fillMaxSize()
           //    .padding(12.dp)) {
           //    Text(
           //        text = article.title,
           //        color = colorResource(id = R.color.black),
           //        textAlign = TextAlign.Left,
           //        style = MaterialTheme.typography.h6
           //    )
           //    Text(
           //        text = "By ${article.author} - ${article.date}",
           //        color = colorResource(id = R.color.date_color),
           //        textAlign = TextAlign.Left,
           //        style = MaterialTheme.typography.h6
           //    )
           //}

        }

    }
}
