package com.vulture.superradio.ui.screens.radiostations

import com.vulture.superradio.ui.models.Station

sealed class RadioStationsState

data class RadioStationsLoaded(
    val items: List<Station>
) : RadioStationsState()

data class RadioStationsError(
    val message: String?
) : RadioStationsState()

object RadioStationsLoading : RadioStationsState()