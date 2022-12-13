package com.vulture.superradio.ui.screens.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vulture.superradio.data.repository.player.PlayerRepository
import com.vulture.superradio.ui.models.Station
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//TODO define player behaviour
@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val playerRepository: PlayerRepository,
) : ViewModel() {

    private val _state: MutableStateFlow<PlayerState> = MutableStateFlow(value = PlayerIsPlaying(isPlaying = false))
    val state: StateFlow<PlayerState> = _state

    fun play(station: Station) {
        viewModelScope.launch {
            playerRepository.play(station)
        }
    }

    fun stop() {

    }

    fun playNext() {
        viewModelScope.launch {
            playerRepository.playNex()
        }
    }

    fun playPrevious() {
        viewModelScope.launch {
            playerRepository.playPrevious()
        }
    }

}