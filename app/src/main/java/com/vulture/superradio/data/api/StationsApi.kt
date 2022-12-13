package com.vulture.superradio.data.api

import com.vulture.superradio.data.api.models.StationPojo
import retrofit2.Response
import retrofit2.http.GET

interface StationsApi {

    @GET("/json/stations")
    suspend fun getStations() : Response<List<StationPojo>>

}