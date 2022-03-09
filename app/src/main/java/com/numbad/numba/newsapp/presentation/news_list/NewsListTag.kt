package com.numbad.numba.newsapp.presentation.news_list

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.numbad.numba.newsapp.R

@Preview
@Composable
fun NewsListTag(

) {
    val tag = "Football"
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.primary),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(10.dp)
    ) {
        Text(
            text = tag,
            color = colorResource(id = R.color.white),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2
        )
    }
}