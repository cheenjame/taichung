package com.example.taichung.data.model

import com.google.gson.annotations.SerializedName

data class FlowerSpotsResponse(
    @SerializedName("data")
    val spots: List<FlowerSpot>
)

data class FlowerSpot(
    @SerializedName("name")
    val name: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("flowerType")
    val flowerType: String,
    @SerializedName("bestViewingTime")
    val bestViewingTime: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
) 