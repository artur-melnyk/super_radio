package com.vulture.superradio.screens.player

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
import com.vulture.superradio.data.models.Station
import com.vulture.superradio.screens.elements.FavoriteButton
import com.vulture.superradio.screens.elements.NavigateBackButton
import com.vulture.superradio.screens.radiostations.RadioStationState
import com.vulture.superradio.ui.theme.mediumIconSize

@Composable
fun PlayerScreen() {
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

    Column(modifier = Modifier.fillMaxSize()) {
        NavigateBackButton(onClick = {/*TODO*/ })
        Row {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Text(
                    text = "Super station",
                    fontSize = 20.sp,
                )
                Text(
                    text = "Genre",
                    fontSize = 20.sp,
                )
            }
            FavoriteButton(
                isFavorite = state.stationState.isFavourite,
                onClick = { /*TODO*/ }
            )
        }
        Image(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            imageVector = Icons.Default.MusicNote,
            contentDescription = "Station Image"
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    modifier = Modifier.size(mediumIconSize),
                    imageVector = Icons.Default.SkipPrevious,
                    contentDescription = "Previous Station"
                )
            }

            val playIcon = if (state.isPlaying) {
                Icons.Default.StopCircle
            } else {
                Icons.Default.PlayCircle
            }

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    modifier = Modifier.size(mediumIconSize),
                    imageVector = playIcon,
                    contentDescription = "Play/Stop"
                )
            }

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    modifier = Modifier.size(mediumIconSize),
                    imageVector = Icons.Default.SkipNext,
                    contentDescription = "Next Station"
                )
            }
        }
    }
}

@Preview(showBackground = true, heightDp = 500, widthDp = 250)
@Composable
fun PlayerPreview() {
    PlayerScreen()
}