package com.numbad.numba.newsapp.presentation.news_details.video

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView
import com.numbad.numba.newsapp.R
import com.numbad.numba.newsapp.common.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@Composable
fun VideoDetailScreen(
    savedStateHandle: Bundle?,
    navHostController: NavHostController,
    viewModel: VideoDetailVM = hiltViewModel(),
){
        val context = LocalContext.current
        val player = ExoPlayer.Builder(context).build()
        val playerView = PlayerView(context)
    val state = viewModel.state.value

        DisposableEffect(key1 = viewModel) {
            onDispose {
                playerView.player?.clearMediaItems()
            }
        }
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
                    state.video?.let { video ->
                        val mediaItem = MediaItem.fromUri(video.url)
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
                    if (state.errorMessage.isNotBlank()) {
                        Text(
                            text = state.errorMessage,
                            color = MaterialTheme.colors.error,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                                .align(Alignment.Center)
                        )
                    }
                    if (state.isLoading) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
        )



}

