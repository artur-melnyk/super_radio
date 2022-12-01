package com.vulture.superradio.screens.radiostations

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vulture.superradio.data.models.Station
import com.vulture.superradio.screens.elements.FavoriteButton
import com.vulture.superradio.ui.theme.SuperRadioTheme
import com.vulture.superradio.ui.theme.largeIconSize


@Composable
fun RadioStationsScreen() {
    RadioStationsList(
        modifier = Modifier,
        state = RadioStationListState(items = emptyList()),
        onItemClick = { station ->
            //TODO
        },
        onFavoriteClick = { station ->
            //TODO
        }
    )
}

@Composable
fun RadioStationsList(
    modifier: Modifier = Modifier,
    state: RadioStationListState,
    onItemClick: (Station) -> Unit,
    onFavoriteClick: (Station) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(state.items) { item ->
            RadioStationItem(
                state = item,
                onItemClick = { onItemClick.invoke(item.station) },
                onFavoriteClick = { onFavoriteClick.invoke(item.station) }
            )
        }
    }
}

@Composable
fun RadioStationItem(
    modifier: Modifier = Modifier,
    state: RadioStationState,
    onItemClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    Row(
        modifier = modifier.clickable(onClick = onItemClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(largeIconSize),
            imageVector = Icons.Default.MusicNote,
            contentDescription = "Station image",
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = state.station.name)
            Text(text = state.station.genre)
        }
        FavoriteButton(
            isFavorite = state.isFavourite,
            onClick = onFavoriteClick
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RadioStationListPreview() {
    val stationsStub = RadioStationListState(
        items = listOf(
            RadioStationState(
                station = Station(
                    name = "Super Station",
                    imageUrl = "",
                    audioSourceUrl = "",
                    genre = "Metal"
                ),
                isFavourite = true
            ),
            RadioStationState(
                station = Station(
                    name = "Mega Station",
                    imageUrl = "",
                    audioSourceUrl = "",
                    genre = "Pop"
                ),
                isFavourite = false
            )
        )
    )

    SuperRadioTheme {
        RadioStationsList(
            state = stationsStub,
            onItemClick = { station ->
                //TODO
            },
            onFavoriteClick = { station ->
                //TODO
            }
        )
    }
}