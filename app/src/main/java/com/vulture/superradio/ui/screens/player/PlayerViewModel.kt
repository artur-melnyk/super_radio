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

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val playerRepository: PlayerRepository,
) : ViewModel() {

    private val _state: MutableStateFlow<PlayerState> = MutableStateFlow(value = PlayerIdle)
    val state: StateFlow<PlayerState> = _state

    init {
        observeIsPlaying()
    }

    fun play(station: Station) {
        viewModelScope.launch {
            playerRepository.play(station)
        }
    }

    fun stop() {
        viewModelScope.launch {
            playerRepository.stop()
        }
    }

    private fun observeIsPlaying() {
        viewModelScope.launch {
            playerRepository.isPlaying().collect { isPlaying ->
                _state.value = if (isPlaying) {
                    PlayerIsPlaying
                } else {
                    PlayerIdle
                }
            }
        }
    }

}