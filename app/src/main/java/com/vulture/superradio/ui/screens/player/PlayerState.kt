package com.vulture.superradio.ui.screens.player

sealed class PlayerState

data class PlayerIsPlaying(
    val isPlaying: Boolean
) : PlayerState()
