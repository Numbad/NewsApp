package com.numbad.numba.newsapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.*
import com.numbad.numba.newsapp.R

@Preview
@Composable
fun LaunchScreen() {
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.primary)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val spec = LottieCompositionSpec.RawRes(R.raw.launch)
        val composition by rememberLottieComposition(spec = spec)
        val stats = animateLottieCompositionAsState(composition = composition, iterations = LottieConstants.IterateForever)

        LottieAnimation(composition = composition, progress =stats.progress )
    }
}