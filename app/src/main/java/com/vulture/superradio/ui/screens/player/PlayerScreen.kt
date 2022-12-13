package com.vulture.superradio.ui.screens.player

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material.icons.filled.StopCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.vulture.superradio.ui.models.Station
import com.vulture.superradio.ui.screens.elements.FavoriteButton
import com.vulture.superradio.ui.screens.elements.NavigateBackButton
import com.vulture.superradio.ui.theme.mediumIconSize

@Destination
@Composable
fun PlayerScreen(
    station: Station,
    navigator: DestinationsNavigator,
    viewModel: PlayerViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    when (val screenState = state) {
        is PlayerIsPlaying -> {
            Column(modifier = Modifier.fillMaxSize()) {
                NavigateBackButton(onClick = {
                    navigator.navigateUp()
                })
                PlayerHeader(
                    station = station,
                    isFavourite = station.isFavourite,
                    onFavouriteClick = {}
                )
                AsyncImage(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    model = station.imageUrl,
                    contentDescription = "Station Image"
                )
                PlayerControl(
                    isPlaying = screenState.isPlaying,
                    onPlayClick = { viewModel.play(station) },
                    onPreviousClick = { viewModel.playPrevious() },
                    onNextClick = { viewModel.playNext() }
                )
            }
        }
    }
}

@Composable
fun Player() {

}

@Composable
fun PlayerHeader(
    station: Station,
    isFavourite: Boolean,
    onFavouriteClick: (Station) -> Unit = {}
) {
    Row {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        ) {
            Text(
                text = station.name,
                fontSize = 20.sp,
            )
            Text(
                text = station.genre,
                fontSize = 20.sp,
            )
        }
        FavoriteButton(
            isFavorite = isFavourite,
            onClick = { onFavouriteClick.invoke(station) }
        )
    }
}

@Composable
fun PlayerControl(
    isPlaying: Boolean,
    onPlayClick: () -> Unit,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        IconButton(onClick = onPreviousClick) {
            Icon(
                modifier = Modifier.size(mediumIconSize),
                imageVector = Icons.Default.SkipPrevious,
                contentDescription = "Previous Station"
            )
        }

        val playIcon = if (isPlaying) {
            Icons.Default.StopCircle
        } else {
            Icons.Default.PlayCircle
        }

        IconButton(onClick = onPlayClick) {
            Icon(
                modifier = Modifier.size(mediumIconSize),
                imageVector = playIcon,
                contentDescription = "Play/Stop"
            )
        }

        IconButton(onClick = onNextClick) {
            Icon(
                modifier = Modifier.size(mediumIconSize),
                imageVector = Icons.Default.SkipNext,
                contentDescription = "Next Station"
            )
        }
    }
}