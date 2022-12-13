package com.vulture.superradio.ui.screens.player

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.vulture.superradio.data.models.Station
import com.vulture.superradio.ui.screens.elements.FavoriteButton
import com.vulture.superradio.ui.screens.elements.NavigateBackButton
import com.vulture.superradio.ui.screens.radiostations.RadioStationState
import com.vulture.superradio.ui.theme.mediumIconSize

@Composable
fun PlayerScreen(
    state: PlayerState,
    onBackPressed: () -> Unit = {},
    onFavouriteClick: (Station) -> Unit = {},
    onPlayClick: (Station) -> Unit = {},
    onNextClick: () -> Unit = {},
    onPreviousClick: () -> Unit = {},
) {
    Column(modifier = Modifier.fillMaxSize()) {
        NavigateBackButton(onClick = onBackPressed)
        PlayerHeader(
            station = state.stationState.station,
            isFavourite = state.stationState.isFavourite,
            onFavouriteClick = onFavouriteClick
        )
        AsyncImage(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            model = state.stationState.station.imageUrl,
            contentDescription = "Station Image"
        )
        PlayerControl(
            isPlaying = state.isPlaying,
            onPlayClick = { onPlayClick.invoke(state.stationState.station) },
            onPreviousClick = onPreviousClick,
            onNextClick = onNextClick
        )
    }
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

@Preview(showBackground = true, heightDp = 500, widthDp = 250)
@Composable
fun PlayerPreview() {
    val state = PlayerState(
        stationState = RadioStationState(
            station = Station(
                name = "Super Station",
                imageUrl = "",
                audioSourceUrl = "",
                genre = "Metal"
            ),
            isFavourite = true
        ),
        isPlaying = true
    )

    PlayerScreen(state)
}