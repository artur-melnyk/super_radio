package com.vulture.superradio.service.music

import android.util.Log
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

/**
 * Provided with Hilt in EventsModule,
 * heps establish flow between service and repository
 */
class MusicServiceConnection {
    private val _events = MutableSharedFlow<MusicEvent>()
    val events = _events.asSharedFlow()

    suspend fun emitEvent(event: MusicEvent) {
        Log.d(this::class.simpleName, "Emitting event = $event")
        _events.emit(event)
    }
}