package com.numbad.numba.newsapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.numbad.numba.newsapp.R

@Preview
@Composable
fun LaunchScreen() {
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val spec = LottieCompositionSpec.RawRes(R.raw.launch)
        val composition by rememberLottieComposition(spec = spec)
        val stats = animateLottieCompositionAsState(composition = composition, iterations = LottieConstants.IterateForever)

        LottieAnimation(composition = composition, progress =stats.progress )
    }
}