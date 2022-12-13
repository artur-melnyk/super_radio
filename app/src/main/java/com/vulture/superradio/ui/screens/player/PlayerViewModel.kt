package com.vulture.superradio.ui.screens.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vulture.superradio.data.models.Station
import com.vulture.superradio.data.repository.player.PlayerRepository
import com.vulture.superradio.data.repository.station.StationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

//TODO define player behaviour
class PlayerViewModel(
    private val stationRepository: StationRepository,
    private val playerRepository: PlayerRepository,
) : ViewModel() {

    private val _state: MutableStateFlow<PlayerState?> = MutableStateFlow(value = null)
    val state : StateFlow<PlayerState?> = _state

    fun play(station: Station) {
        viewModelScope.launch {
            playerRepository.play(station)
        }
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

    fun loadStation() {
        viewModelScope.launch {
            stationRepository.getStations()
        }
    }

}