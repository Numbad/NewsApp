package com.numbad.numba.newsapp.presentation.news_details.video

import android.content.Intent
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
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView
import com.numbad.numba.newsapp.R
import com.numbad.numba.newsapp.common.Constant


@Composable
fun VideoDetailScreen(savedStateHandle: Bundle?, navHostController: NavHostController){
    savedStateHandle?.get(Constant.VIDEO_URL)?.let { link ->
        val context = LocalContext.current
        val player = ExoPlayer.Builder(context).build()
        val playerView = PlayerView(context)
        val mediaItem = MediaItem.fromUri(link.toString())
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("") },
                    navigationIcon = {
                        IconButton(onClick = {
                            player.playWhenReady = false;
                            player.stop()
                            player.release()
                            player.seekTo(0)
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
                                putExtra(Intent.EXTRA_TEXT, "Do you want to share this video ?")
                                type = "text/plain"
                            }
                            val shareIntent = Intent.createChooser(sendIntent, null)
                            context.startActivity(shareIntent)
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Share,
                                contentDescription = "Localized description"
                            )
                        }
                    }, backgroundColor = colorResource(id = R.color.primary)
                )
            },
            content = {
                Box {
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

