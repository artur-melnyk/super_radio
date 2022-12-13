package com.vulture.superradio.ui.screens.radiostations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vulture.superradio.data.repository.station.StationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RadioStationsViewModel @Inject constructor(
    private val stationRepository: StationRepository
) : ViewModel() {

    private val _state: MutableStateFlow<RadioStationListState?> = MutableStateFlow(value = null)
    val state: StateFlow<RadioStationListState?> = _state

    init {
        loadStations()
    }

    fun loadStations() {
        viewModelScope.launch {
            stationRepository
                .getStations()
                .collect { stations ->
                    _state.value = RadioStationListState(
                        stations.map {
                            RadioStationState(
                                station = it,
                                isFavourite = it.isFavourite
                            )
                        }
                    )
                }
        }
    }

}