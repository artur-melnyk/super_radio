package com.vulture.superradio.data.repository.station

import com.vulture.superradio.data.models.Station
import kotlinx.coroutines.flow.Flow

interface StationRepository {
    fun getStations() : Flow<List<Station>>
}