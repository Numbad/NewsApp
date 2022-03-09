package com.numbad.numba.newsapp.presentation.news_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.numbad.numba.newsapp.R

@Composable
fun NewsListTag(
    tag: String
) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(5.dp))

    ) {
        Text(
            text = tag,
            color = colorResource(id = R.color.white),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .background(colorResource(id = R.color.primary))
                .padding(5.dp)

        )
    }
}