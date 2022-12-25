package com.vulture.superradio.data.repository.player

import com.vulture.superradio.service.music.*
import com.vulture.superradio.ui.models.Station
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlayerRepositoryImpl @Inject constructor(
    private val musicServiceConnection: MusicServiceConnection
) : PlayerRepository {

    override suspend fun play(station: Station) {
        musicServiceConnection.emitEvent(MusicPlay(station))
    }

    override suspend fun stop() {
        musicServiceConnection.emitEvent(MusicStop)
    }

    override fun isPlaying(): Flow<Boolean> = flow {
        musicServiceConnection.events.collect { event ->
            when (event) {
                is MusicIsPlaying -> emit(event.isPlaying)
                else -> {
                    //nothing
                }
            }
        }
    }
}