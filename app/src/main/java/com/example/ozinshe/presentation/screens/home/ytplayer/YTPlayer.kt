package com.example.ozinshe.presentation.screens.home.ytplayer

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.ozinshe.presentation.utils.toAlbumOrientation
import io.github.ilyapavlovskii.multiplatform.youtubeplayer.SimpleYouTubePlayerOptionsBuilder
import io.github.ilyapavlovskii.multiplatform.youtubeplayer.YouTubePlayer
import io.github.ilyapavlovskii.multiplatform.youtubeplayer.YouTubePlayerHostState
import io.github.ilyapavlovskii.multiplatform.youtubeplayer.YouTubePlayerState
import io.github.ilyapavlovskii.multiplatform.youtubeplayer.YouTubeVideoId
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun YTPlayer(
    movieLink: String?
) {
    toAlbumOrientation()

    val coroutineScope = rememberCoroutineScope()
    val hostState = remember { YouTubePlayerHostState() }

    when(val state = hostState.currentState) {
        is YouTubePlayerState.Error -> {
            Text(text = "Error: ${state.message}")
        }
        YouTubePlayerState.Idle -> {
            // Do nothing, waiting for initialization
        }
        is YouTubePlayerState.Playing -> {

        }
        YouTubePlayerState.Ready -> coroutineScope.launch {
            hostState.loadVideo(YouTubeVideoId(movieLink!!))
        }
    }

    YouTubePlayer(
        modifier = Modifier
            .fillMaxSize(),
        hostState = hostState,
        options = SimpleYouTubePlayerOptionsBuilder.builder {
            autoplay(false)
            controls(true).rel(showRelatedVideos = false)
            ivLoadPolicy(false)
            ccLoadPolicy(false)
            fullscreen = false
        }
    )
}