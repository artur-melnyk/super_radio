package com.vulture.superradio.ui.screens.player

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import com.vulture.superradio.ui.screens.elements.PlayButton

@Destination
@Composable
fun PlayerScreen(
    station: Station, navigator: DestinationsNavigator, viewModel: PlayerViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = null) {
        viewModel.play(station)
    }

    val state by viewModel.state.collectAsState()

    var isPlaying by remember {
        mutableStateOf(false)
    }

    isPlaying = when (state) {
        is PlayerIsPlaying -> {
            true
        }
        PlayerIdle -> {
            false
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        NavigateBackButton(onClick = {
            navigator.navigateUp()
        })
        PlayerHeader(station = station, isFavourite = station.isFavourite, onFavouriteClick = {})
        AsyncImage(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            model = station.imageUrl,
            contentDescription = "Station Image"
        )
        PlayerControl(
            isPlaying = isPlaying,
            onPlayClick = {
                if (isPlaying) {
                    viewModel.stop()
                } else {
                    viewModel.play(station)
                }
            }
        )
    }

}

@Composable
fun Player() {

}

@Composable
fun PlayerHeader(
    station: Station, isFavourite: Boolean, onFavouriteClick: (Station) -> Unit = {}
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
        FavoriteButton(isFavorite = isFavourite, onClick = { onFavouriteClick.invoke(station) })
    }
}

@Composable
fun PlayerControl(
    isPlaying: Boolean,
    onPlayClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        PlayButton(isPlaying = isPlaying, onClick = onPlayClick)
    }
}