package com.vulture.superradio.screens.radiostations

import com.vulture.superradio.data.models.Station

data class RadioStationListState(
    val items: List<RadioStationState>
)

data class RadioStationState(
    val station: Station,
    val isFavourite: Boolean,
)