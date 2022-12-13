package com.vulture.superradio.data.repository.player

import com.vulture.superradio.data.models.Station

interface PlayerRepository {
    fun play(station: Station)
    fun playNex()
    fun playPrevious()
}