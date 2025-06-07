package com.example.taichung.data.model

import com.google.gson.annotations.SerializedName

data class FlowerSpotsResponse(
    @SerializedName("result")
    val result: FlowerSpotsResult? = null,
    // 直接的資料列表也支援
    @SerializedName("records")
    val records: List<FlowerSpot>? = null,
    // 其他可能的格式
    @SerializedName("data")
    val data: List<FlowerSpot>? = null
)

data class FlowerSpotsResult(
    @SerializedName("records")
    val spots: List<FlowerSpot>
)

data class FlowerSpot(
    @SerializedName("縣市")
    val city: String? = null,
    @SerializedName("行政區")
    val district: String? = null,
    @SerializedName("地點")
    val name: String? = null,
    @SerializedName("地址")
    val address: String? = null,
    @SerializedName("花種")
    val flowerType: String? = null,
    @SerializedName("觀賞時期")
    val bestViewingTime: String? = null
) {
    val safeName: String get() = name ?: "未知景點"
    val safeAddress: String get() = address ?: "地址不詳"
    val safeFlowerType: String get() = flowerType ?: "花種不詳"
    val safeBestViewingTime: String get() = bestViewingTime ?: "觀賞期不詳"
    val safeDistrict: String get() = district ?: ""
} 