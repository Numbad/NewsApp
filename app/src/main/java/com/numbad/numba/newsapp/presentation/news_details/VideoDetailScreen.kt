package com.numbad.numba.newsapp.presentation.news_details

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.MediaItem
import com.numbad.numba.newsapp.common.Constant


@Composable
fun VideoDetailScreen(savedStateHandle: Bundle?){
    savedStateHandle?.get(Constant.VIDEO_URL)?.let { link ->
        val context = LocalContext.current
        val player = SimpleExoPlayer.Builder(context).build()
        val playerView = PlayerView(context)
        val mediaItem = MediaItem.fromUri(link.toString())

        player.setMediaItem(mediaItem)
        playerView.player = player
        LaunchedEffect(player) {

            player.prepare()
            player.playWhenReady = false

        }
        AndroidView(factory = {
            playerView
        }, modifier = Modifier.fillMaxSize())
    }

}

