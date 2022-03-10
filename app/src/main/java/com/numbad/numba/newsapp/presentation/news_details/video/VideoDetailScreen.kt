package com.numbad.numba.newsapp.presentation.news_details

import android.os.Bundle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.numbad.numba.newsapp.R
import com.numbad.numba.newsapp.common.Constant


@Composable
fun VideoDetailScreen(savedStateHandle: Bundle?, navHostController: NavHostController){
    savedStateHandle?.get(Constant.VIDEO_URL)?.let { link ->
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
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(
                                imageVector = Icons.Filled.Share,
                                contentDescription = "Localized description"
                            )
                        }
                    }, backgroundColor = colorResource(id = R.color.primary)
                )
            },
            content = { innerPadding ->
                Box {

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
        )

    }

}

