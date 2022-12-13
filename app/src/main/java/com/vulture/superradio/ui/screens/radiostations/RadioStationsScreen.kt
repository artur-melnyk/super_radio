package com.vulture.superradio.ui.screens.radiostations

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.vulture.superradio.ui.models.Station
import com.vulture.superradio.ui.screens.destinations.PlayerScreenDestination
import com.vulture.superradio.ui.screens.elements.FavoriteButton
import com.vulture.superradio.ui.theme.largeIconSize

@Destination(start = true)
@Composable
fun RadioStationsScreen(
    navigator: DestinationsNavigator,
    viewModel: RadioStationsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    when (val screenState = state) {
        is RadioStationsLoading -> {
            //todo
        }
        is RadioStationsLoaded -> {
            RadioStationsList(
                modifier = Modifier,
                stations = screenState.items,
                onItemClick = { station ->
                    navigator.navigate(
                        PlayerScreenDestination(
                            station = station
                        )
                    )
                },
                onFavoriteClick = {
                    //todo
                }
            )
        }
        is RadioStationsError -> {
            //todo
        }
    }
}

@Composable
fun RadioStationsList(
    modifier: Modifier = Modifier,
    stations: List<Station>,
    onItemClick: (Station) -> Unit,
    onFavoriteClick: (Station) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(stations) { station ->
            RadioStationItem(
                station = station,
                onItemClick = { onItemClick.invoke(station) },
                onFavoriteClick = { onFavoriteClick.invoke(station) }
            )
        }
    }
}

@Composable
fun RadioStationItem(
    modifier: Modifier = Modifier,
    station: Station,
    onItemClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    Row(
        modifier = modifier.clickable(onClick = onItemClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier.size(largeIconSize),
            model = station.imageUrl,
            contentDescription = "Station image",
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = station.name)
            Text(text = station.genre)
        }
        FavoriteButton(
            isFavorite = station.isFavourite,
            onClick = onFavoriteClick
        )
    }
}