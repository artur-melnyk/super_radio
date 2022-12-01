package com.vulture.superradio.screens.player

import com.vulture.superradio.screens.radiostations.RadioStationState

data class PlayerState(
    val stationState: RadioStationState,
    val isPlaying: Boolean
)
