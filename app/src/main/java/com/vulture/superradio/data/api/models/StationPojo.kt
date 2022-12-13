package com.vulture.superradio.data.api.models

import com.google.gson.annotations.SerializedName

data class StationPojo(
    @SerializedName("stationuuid")
    val uuid: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("url_resolved")
    val streamUrl: String,
    @SerializedName("tags")
    val tags: String,
    @SerializedName("favicon")
    val imageUrl: String
)
