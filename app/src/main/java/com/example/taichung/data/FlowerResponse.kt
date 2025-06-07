package com.example.taichung.data

import com.google.gson.annotations.SerializedName

data class FlowerResponse(
    @SerializedName("data") val data: List<FlowerItem>
)

data class FlowerItem(
    @SerializedName("Name") val name: String?,
    @SerializedName("Picture1") val imageUrl: String?,
    @SerializedName("Website") val website: String?
)
