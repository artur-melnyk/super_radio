package com.vulture.superradio.ui.screens.player

sealed class PlayerState

object PlayerIdle : PlayerState()
object PlayerIsPlaying : PlayerState()
