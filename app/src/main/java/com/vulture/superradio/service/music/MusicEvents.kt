package com.vulture.superradio.service.music

import com.vulture.superradio.ui.models.Station

sealed class MusicEvent

data class MusicPlay(val station: Station) : MusicEvent()
object MusicStop : MusicEvent()

data class MusicIsPlaying(val isPlaying: Boolean) : MusicEvent()