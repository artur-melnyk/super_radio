package com.vulture.superradio.data.repository.station

import com.vulture.superradio.data.models.Station
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StationRepositoryImpl : StationRepository {

    override fun getStations(): Flow<List<Station>> = flow {
        //get from api
    }

}