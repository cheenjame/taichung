package com.example.taichung.data.model

import com.google.gson.annotations.SerializedName

data class MarineRecreationResponse(
    @SerializedName("data")
    val spots: List<MarineRecreationSpot>
)

data class MarineRecreationSpot(
    @SerializedName("name")
    val name: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
) 