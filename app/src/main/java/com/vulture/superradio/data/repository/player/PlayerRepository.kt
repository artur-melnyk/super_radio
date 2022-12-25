package com.vulture.superradio.data.repository.player

import com.vulture.superradio.ui.models.Station
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {
    suspend fun play(station: Station)
    suspend fun stop()
    fun isPlaying(): Flow<Boolean>
}