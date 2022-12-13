package com.vulture.superradio.ui.screens.player

import com.vulture.superradio.ui.screens.radiostations.RadioStationState

data class PlayerState(
    val stationState: RadioStationState,
    val isPlaying: Boolean
)
